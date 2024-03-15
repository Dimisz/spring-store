package com.uningen.estore.domain.order;

public class NoOrdersFoundException extends RuntimeException{
    public NoOrdersFoundException(){
        super("Could not find any orders");
    }
}
