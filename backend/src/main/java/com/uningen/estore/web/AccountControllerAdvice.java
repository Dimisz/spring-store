package com.uningen.estore.web;


import com.uningen.estore.domain.user.EmailAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AccountControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String userNameNotFoundHandler(UsernameNotFoundException ex){
        return "No user found";
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String emailAlreadyInUseHandler(EmailAlreadyRegisteredException ex){
        return ex.getMessage();
    }
}
