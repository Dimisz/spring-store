package com.uningen.estore.web;


import com.uningen.estore.domain.order.NoOrdersFoundException;
import com.uningen.estore.domain.order.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrdersControllerAdvice {
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderNotFoundHandler(OrderNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(NoOrdersFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String noOrdersFoundHandler(NoOrdersFoundException ex){
        return ex.getMessage();
    }
}
