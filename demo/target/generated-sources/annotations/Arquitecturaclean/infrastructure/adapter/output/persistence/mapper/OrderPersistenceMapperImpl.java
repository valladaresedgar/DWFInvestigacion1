package Arquitecturaclean.infrastructure.adapter.output.persistence.mapper;

import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.vo.Money;
import Arquitecturaclean.domain.vo.OrderId;
import Arquitecturaclean.infrastructure.adapter.output.persistence.entity.OrderJpaEntity;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T19:49:04-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class OrderPersistenceMapperImpl implements OrderPersistenceMapper {

    @Override
    public OrderJpaEntity toJpaEntity(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderJpaEntity orderJpaEntity = new OrderJpaEntity();

        orderJpaEntity.setId( orderIdValue( order ) );
        orderJpaEntity.setStatus( mapStatusToJpa( order.getStatus() ) );
        orderJpaEntity.setCreatedAt( order.getCreatedAt() );
        BigDecimal amount = orderTotalAmount( order );
        if ( amount != null ) {
            orderJpaEntity.setTotal( amount.doubleValue() );
        }
        orderJpaEntity.setItems( mapItemsToJpa( order.getItems() ) );

        return orderJpaEntity;
    }

    @Override
    public Order toDomainEntity(OrderJpaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( mapIdToDomain( entity.getId() ) );
        order.setStatus( mapStatusToDomain( entity.getStatus() ) );
        order.setCreatedAt( entity.getCreatedAt() );
        order.setTotal( mapMoneyToDomain( entity.getTotal() ) );
        order.setItems( mapItemsToDomain( entity.getItems() ) );

        return order;
    }

    private String orderIdValue(Order order) {
        if ( order == null ) {
            return null;
        }
        OrderId id = order.getId();
        if ( id == null ) {
            return null;
        }
        String value = id.getValue();
        if ( value == null ) {
            return null;
        }
        return value;
    }

    private BigDecimal orderTotalAmount(Order order) {
        if ( order == null ) {
            return null;
        }
        Money total = order.getTotal();
        if ( total == null ) {
            return null;
        }
        BigDecimal amount = total.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }
}
