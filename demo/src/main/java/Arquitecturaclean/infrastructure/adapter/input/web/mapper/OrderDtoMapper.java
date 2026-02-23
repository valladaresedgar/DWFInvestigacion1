package Arquitecturaclean.infrastructure.adapter.input.web.mapper;

import Arquitecturaclean.domain.entity.Order;
import Arquitecturaclean.infrastructure.adapter.input.web.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "total", source = "total.amount")
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemsToDto")
    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponseList(List<Order> orders);

    @Named("mapItemsToDto")
    default List<OrderResponse.OrderItemResponse> mapItemsToDto(List<Order.OrderItem> items) {
        if (items == null) return null;
        return items.stream()
                .map(item -> {
                    OrderResponse.OrderItemResponse dto = new OrderResponse.OrderItemResponse();
                    dto.setProductId(item.getProduct().getId());
                    dto.setProductName(item.getProduct().getName());
                    dto.setQuantity(item.getQuantity());
                    dto.setSubtotal(item.getSubtotal().getAmount().doubleValue());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}