package com.uningen.estore.domain.cart;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(Long cartId){
        super("Could not find cart with id " + cartId);
    }
}
