package Arquitecturaclean.application.service;

import Arquitecturaclean.application.port.input.CreateOrderUseCase;
import Arquitecturaclean.application.port.output.OrderRepository;
import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.entity.Product;
import Arquitecturaclean.domain.vo.Money;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public CreateOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(List<ProductRequest> products) {
        Order order = new Order();

        products.forEach(req -> {
            Product product = findProductById(req.productId());
            order.addProduct(product, req.quantity());
        });

        if (order.getTotal().getAmount().doubleValue() > 1000) {
            order.applyDiscount(10);
        }

        return orderRepository.save(order);
    }

    private Product findProductById(String id) {
        return new Product(id, "Producto " + id, Money.of(100));
    }
}