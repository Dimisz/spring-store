package com.uningen.estore.domain.cart;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String cartId){
        super("Could not find cart with id " + cartId);
    }
}
