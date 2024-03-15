package com.uningen.estore.domain.user;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String email){
        super("Someone has already registered with this email " + email);
    }
}
