package Arquitecturaclean.application.port.input;

import Arquitecturaclean.domain.entity.Order;
import java.util.List;

public interface CreateOrderUseCase {
    Order createOrder(List<ProductRequest> products);
    record ProductRequest(String productId, int quantity) {}
}