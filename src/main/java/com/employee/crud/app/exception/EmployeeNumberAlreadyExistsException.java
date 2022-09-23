package com.employee.crud.app.exception;

public class EmployeeNumberAlreadyExistsException extends RuntimeException{
    public EmployeeNumberAlreadyExistsException(String message){
        super(message);
    }
}
