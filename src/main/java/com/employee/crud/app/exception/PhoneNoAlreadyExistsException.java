package com.employee.crud.app.exception;

public class PhoneNoAlreadyExistsException extends RuntimeException{
    public PhoneNoAlreadyExistsException(String message){
        super(message);
    }
}
