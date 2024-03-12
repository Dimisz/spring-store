package com.uningen.estore.domain.cart;

import com.uningen.estore.domain.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String name;
    private double price;
    private String pictureUrl;
    private String brand;
    private String category;
    private int quantity;
    public CartItem(Product product, int qty){
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.pictureUrl = product.getPictureUrl();
        this.brand = product.getBrand();
        this.category = product.getCategory();
        this.quantity = qty;
    }
}
