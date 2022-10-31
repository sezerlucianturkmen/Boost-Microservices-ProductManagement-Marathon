package com.bilgeadam.exception;
import lombok.Getter;

@Getter
public class StockManagerException extends RuntimeException{
    private final ErrorType errorType;

    public StockManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public StockManagerException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }
}