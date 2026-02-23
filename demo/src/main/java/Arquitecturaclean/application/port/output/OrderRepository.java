package Arquitecturaclean.application.port.output;

import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.vo.OrderId;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(OrderId id);
}