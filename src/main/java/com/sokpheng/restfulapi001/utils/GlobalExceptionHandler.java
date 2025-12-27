package com.sokpheng.restfulapi001.utils;

import com.sokpheng.restfulapi001.exception.CategoryNotFoundException;
import com.sokpheng.restfulapi001.exception.CustomerException;
import com.sokpheng.restfulapi001.exception.NotFoundOrderException;
import com.sokpheng.restfulapi001.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIErrorResponse> validateHandling
            (MethodArgumentNotValidException exception){
        APIErrorResponse apiErrorResponse
                = APIErrorResponse.builder()
                .status(exception.getStatusCode().toString())
                .timeStamp(Date.from(Instant.now()))
                .errorMessage(exception.getFieldError()
                        .getDefaultMessage())
                .build();
        return ResponseEntity.badRequest().body(apiErrorResponse);
    }
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<APIErrorResponse>
    handleCustomerException(CustomerException exception){
        APIErrorResponse apiErrorResponse
                = APIErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .timeStamp(Date.from(Instant.now()))
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleProductNotFound(ProductNotFoundException exception){
        APIErrorResponse apiErrorResponse
                = APIErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .timeStamp(Date.from(Instant.now()))
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleCategoryNotFound(CategoryNotFoundException exception){
        APIErrorResponse apiErrorResponse
                = APIErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .timeStamp(Date.from(Instant.now()))
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }
    @ExceptionHandler(NotFoundOrderException.class)
    public ResponseEntity<APIErrorResponse> handleOrderNotFound(NotFoundOrderException exception){
        APIErrorResponse apiErrorResponse
                = APIErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .timeStamp(Date.from(Instant.now()))
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiErrorResponse);
    }
}

