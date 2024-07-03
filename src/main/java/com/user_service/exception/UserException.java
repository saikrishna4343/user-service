package com.user_service.exception;

public class UserException extends RuntimeException{
    private final String errorMessage;

    public UserException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
