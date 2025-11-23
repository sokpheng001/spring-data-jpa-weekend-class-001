package com.sokpheng.restfulapi001.exception;

import com.sokpheng.restfulapi001.exception.customexceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleForDataNotSubmitting(HttpMessageNotReadableException exception){
        return "You missed the data submit 🤦‍♂️";
    }
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundException(UserException exception){
        return exception.getMessage();
    }
}
