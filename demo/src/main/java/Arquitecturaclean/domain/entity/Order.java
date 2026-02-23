package Arquitecturaclean.domain.entity;

import Arquitecturaclean.domain.vo.Money;
import Arquitecturaclean.domain.vo.OrderId;
import Arquitecturaclean.domain.vo.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Order {
    @Getter
    private final OrderId id;
    private List<OrderItem> items;
    @Getter
    private OrderStatus status;
    @Getter
    private LocalDateTime createdAt;
    @Getter
    private Money total;

    public Order() {
        this.id = OrderId.generate();
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.total = Money.of(0);
    }

    // Inner class OrderItem
    public static class OrderItem {
        private final Product product;
        private final int quantity;
        private final Money subtotal;

        public OrderItem(Product product, int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser positiva");
            }
            this.product = product;
            this.quantity = quantity;
            this.subtotal = product.getPrice().multiply(quantity);
        }

        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }
        public Money getSubtotal() { return subtotal; }
    }

    // Business methods
    public void addProduct(Product product, int quantity) {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("No se puede modificar un pedido que no está en estado CREADO");
        }
        items.add(new OrderItem(product, quantity));
        recalculateTotal();
    }

    public void applyDiscount(double percentage) {
        if (percentage <= 0 || percentage > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100");
        }
        double discountFactor = (100 - percentage) / 100;
        Money currentTotal = this.total;
        this.total = Money.of(currentTotal.getAmount().multiply(
                java.math.BigDecimal.valueOf(discountFactor)));
    }

    private void recalculateTotal() {
        this.total = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(Money.of(0), Money::add);
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    // === SETTERS PARA MAPSTRUCT ===
    public void setId(OrderId id) {
        // id es final, no se puede modificar
        // Este setter existe solo para satisfacer a MapStruct
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}