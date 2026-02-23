package Arquitecturaclean.infrastructure.adapter.input.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private List<ProductItem> products;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductItem {
        private String productId;
        private int quantity;
    }

    // Getters y setters explícitos para evitar errores de compilación
    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
    }
}