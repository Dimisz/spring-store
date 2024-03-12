package com.uningen.estore.web;

import com.uningen.estore.domain.cart.InsufficientQuantityAvailableException;
import com.uningen.estore.domain.cart.CartNotFoundException;
import com.uningen.estore.domain.cart.NegativeQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsMap.put(fieldName, errorMessage);
        });
        return errorsMap;
    }
}
