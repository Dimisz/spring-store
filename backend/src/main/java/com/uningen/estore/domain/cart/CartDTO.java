package com.uningen.estore.domain.cart;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * shaping cart data to return to the Frontend App
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CartDTO {
    private String cartId;
    private String buyerId;
    private List<CartItem> items;
    private String paymentIntentId; // used for payments with stripe
    private String clientSecret; // used for payments with stripe
}
