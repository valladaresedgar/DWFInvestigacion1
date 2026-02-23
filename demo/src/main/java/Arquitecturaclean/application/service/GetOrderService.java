package Arquitecturaclean.application.service;

import Arquitecturaclean.application.port.input.GetOrderUseCase;
import Arquitecturaclean.application.port.output.OrderRepository;
import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.vo.OrderId;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class GetOrderService implements GetOrderUseCase {

    private final OrderRepository orderRepository;

    public GetOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(OrderId id) {
        if (id == null || id.getValue() == null) {
            throw new IllegalArgumentException("El ID de la orden no puede ser null");
        }
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado: " + id.getValue()));
    }
}