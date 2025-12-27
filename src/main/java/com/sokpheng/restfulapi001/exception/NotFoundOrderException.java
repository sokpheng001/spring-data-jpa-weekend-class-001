package com.sokpheng.restfulapi001.exception;

public class NotFoundOrderException extends RuntimeException{
    public NotFoundOrderException(String message){
        super(message);
    }
}
