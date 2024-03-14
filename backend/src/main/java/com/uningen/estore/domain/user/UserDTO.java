package com.uningen.estore.domain.user;

import com.uningen.estore.domain.cart.CartDTO;
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
}
