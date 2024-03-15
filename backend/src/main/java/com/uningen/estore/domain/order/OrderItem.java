package com.uningen.estore.domain.order;

import com.uningen.estore.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long productId;
    private String name;
    private String pictureUrl;
    private double price;
    private int quantity;

    public OrderItem(Product product, Integer productQty) {
        this.productId = product.getId();
        this.name = product.getName();
        this.pictureUrl = product.getPictureUrl();
        this.price = product.getPrice();
        this.quantity = productQty;
    }
}
