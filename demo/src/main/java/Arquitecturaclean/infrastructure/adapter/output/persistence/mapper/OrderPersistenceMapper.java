package Arquitecturaclean.infrastructure.adapter.output.persistence.mapper;

import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.entity.Product;
import Arquitecturaclean.domain.vo.Money;
import Arquitecturaclean.domain.vo.OrderId;
import Arquitecturaclean.domain.vo.OrderStatus;
import Arquitecturaclean.infrastructure.adapter.output.persistence.entity.OrderItemJpaEntity;
import Arquitecturaclean.infrastructure.adapter.output.persistence.entity.OrderJpaEntity;
import Arquitecturaclean.infrastructure.adapter.output.persistence.entity.OrderStatusJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderPersistenceMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatusToJpa")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "total", source = "total.amount")
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemsToJpa")
    OrderJpaEntity toJpaEntity(Order order);

    @Mapping(target = "id", source = "id", qualifiedByName = "mapIdToDomain")
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatusToDomain")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "total", source = "total", qualifiedByName = "mapMoneyToDomain")
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemsToDomain")
    Order toDomainEntity(OrderJpaEntity entity);

    @Named("mapStatusToJpa")
    default OrderStatusJpa mapStatusToJpa(OrderStatus status) {
        if (status == null) return null;
        return OrderStatusJpa.valueOf(status.name());
    }

    @Named("mapStatusToDomain")
    default OrderStatus mapStatusToDomain(OrderStatusJpa status) {
        if (status == null) return null;
        return OrderStatus.valueOf(status.name());
    }

    @Named("mapIdToDomain")
    default OrderId mapIdToDomain(String id) {
        if (id == null) return null;
        return OrderId.of(id);
    }

    @Named("mapMoneyToDomain")
    default Money mapMoneyToDomain(Double total) {
        if (total == null) return null;
        return Money.of(total);
    }

    @Named("mapItemsToJpa")
    default List<OrderItemJpaEntity> mapItemsToJpa(List<Order.OrderItem> items) {
        if (items == null) return null;
        return items.stream()
                .map(item -> {
                    OrderItemJpaEntity entity = new OrderItemJpaEntity();
                    entity.setProductId(item.getProduct().getId());
                    entity.setProductName(item.getProduct().getName());
                    entity.setQuantity(item.getQuantity());
                    entity.setSubtotal(item.getSubtotal().getAmount().doubleValue());
                    return entity;
                })
                .collect(Collectors.toList());
    }

    @Named("mapItemsToDomain")
    default List<Order.OrderItem> mapItemsToDomain(List<OrderItemJpaEntity> items) {
        if (items == null) return null;
        return items.stream()
                .map(item -> {
                    // Calcular precio unitario de forma segura
                    double unitPrice = item.getQuantity() > 0 ?
                            item.getSubtotal() / item.getQuantity() : 0.0;

                    Product product = new Product(
                            item.getProductId(),
                            item.getProductName(),
                            Money.of(unitPrice)
                    );
                    return new Order.OrderItem(product, item.getQuantity());
                })
                .collect(Collectors.toList());
    }
}