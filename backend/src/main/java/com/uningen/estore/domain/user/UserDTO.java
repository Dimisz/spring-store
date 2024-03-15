package com.uningen.estore.domain.user;

import com.uningen.estore.domain.cart.CartDTO;
import com.uningen.estore.domain.order.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String token;
    private CartDTO cart;
    private ShippingAddress shippingAddress;
}
