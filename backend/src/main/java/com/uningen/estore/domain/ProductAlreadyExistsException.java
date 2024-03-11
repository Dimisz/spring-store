package com.uningen.estore.domain;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(Long id){
        super("A product with id " + id + " already exists");
    }
}
