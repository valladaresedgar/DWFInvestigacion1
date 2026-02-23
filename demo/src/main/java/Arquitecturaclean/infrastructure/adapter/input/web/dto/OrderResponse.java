package Arquitecturaclean.infrastructure.adapter.input.web.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private String id;
    private List<OrderItemResponse> items;
    private String status;
    private LocalDateTime createdAt;
    private double total;

    @Data
    public static class OrderItemResponse {
        private String productId;
        private String productName;
        private int quantity;
        private double subtotal;
    }
}