package com.uningen.estore.domain.cart;

public class NegativeQuantityException extends RuntimeException{
    public NegativeQuantityException(int qty){
        super("Somehow a negative quantity value of " + qty + " was passed to the system. Try again with a positive number");
    }
}
