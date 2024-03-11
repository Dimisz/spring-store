package com.uningen.estore.domain;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Product with id " + id + " was not found");
    }
}
