package Arquitecturaclean.application.port.input;

import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.vo.OrderId;

public interface GetOrderUseCase {
    Order getOrder(OrderId id);
}