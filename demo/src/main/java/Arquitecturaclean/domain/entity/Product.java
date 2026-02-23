package Arquitecturaclean.domain.entity;

import Arquitecturaclean.domain.vo.Money;
import java.util.Objects;

public class Product {
    private final String id;
    private String name;
    private Money price;

    public Product(String id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public Money getPrice() { return price; }

    // Business methods
    public void updatePrice(Money newPrice) {
        this.price = newPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}