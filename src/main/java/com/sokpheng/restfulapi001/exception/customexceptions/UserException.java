package com.sokpheng.restfulapi001.exception.customexceptions;

public class UserException extends RuntimeException{
    public UserException(String message){
        super(message);
    }
}
