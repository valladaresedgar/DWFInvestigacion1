package Arquitecturaclean.infrastructure.adapter.input.web;

import Arquitecturaclean.application.port.input.CreateOrderUseCase;
import Arquitecturaclean.application.port.input.GetOrderUseCase;
import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.vo.OrderId;
import Arquitecturaclean.infrastructure.adapter.input.web.dto.OrderRequest;
import Arquitecturaclean.infrastructure.adapter.input.web.dto.OrderResponse;
import Arquitecturaclean.infrastructure.adapter.input.web.mapper.OrderDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final OrderDtoMapper dtoMapper;

    public OrderController(CreateOrderUseCase createOrderUseCase,
                           GetOrderUseCase getOrderUseCase,
                           OrderDtoMapper dtoMapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        var products = request.getProducts().stream()
                .map(p -> new CreateOrderUseCase.ProductRequest(p.getProductId(), p.getQuantity()))
                .collect(Collectors.toList());

        Order order = createOrderUseCase.createOrder(products);
        return new ResponseEntity<>(dtoMapper.toResponse(order), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String id) {
        Order order = getOrderUseCase.getOrder(OrderId.of(id));
        return ResponseEntity.ok(dtoMapper.toResponse(order));
    }
}