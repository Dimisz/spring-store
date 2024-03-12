package com.uningen.estore.domain.cart;

public class InsufficientQuantityAvailableException extends RuntimeException {
    public InsufficientQuantityAvailableException(Long productId, int quantity) {
        super("Unfortunately we don't have product " + productId + " in the quantity of " + quantity + " tems available at the moment");
    }
}
