package com.uningen.estore.web;

import com.uningen.estore.domain.cart.InsufficientQuantityAvailableException;
import com.uningen.estore.domain.cart.CartNotFoundException;
import com.uningen.estore.domain.cart.NegativeQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartControllerAdvice {
    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cartNotFoundHandler(CartNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InsufficientQuantityAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String insufficientQuantityAvailableHandler(InsufficientQuantityAvailableException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(NegativeQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String negativeQuantityExceptionHandler(NegativeQuantityException ex){
        return ex.getMessage();
    }
}
