package Arquitecturaclean.infrastructure.adapter.input.web.mapper;

import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.domain.vo.Money;
import Arquitecturaclean.domain.vo.OrderId;
import Arquitecturaclean.infrastructure.adapter.input.web.dto.OrderResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-22T19:49:05-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class OrderDtoMapperImpl implements OrderDtoMapper {

    @Override
    public OrderResponse toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( orderIdValue( order ) );
        if ( order.getStatus() != null ) {
            orderResponse.setStatus( order.getStatus().name() );
        }
        orderResponse.setCreatedAt( order.getCreatedAt() );
        BigDecimal amount = orderTotalAmount( order );
        if ( amount != null ) {
            orderResponse.setTotal( amount.doubleValue() );
        }
        orderResponse.setItems( mapItemsToDto( order.getItems() ) );

        return orderResponse;
    }

    @Override
    public List<OrderResponse> toResponseList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponse> list = new ArrayList<OrderResponse>( orders.size() );
        for ( Order order : orders ) {
            list.add( toResponse( order ) );
        }

        return list;
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
