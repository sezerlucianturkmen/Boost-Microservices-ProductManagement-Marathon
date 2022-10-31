package com.bilgeadam.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    INVALID_TOKEN(2002, "Invalid Token", BAD_REQUEST),

    STOCK_DUPLICATE(1003, "This is already exist.", INTERNAL_SERVER_ERROR),
    STOCK_NOT_FOUND(1004, "Stock detail is not found", INTERNAL_SERVER_ERROR),
    STOCK_NOT_CREATED(1005, "Stock detail is not created", INTERNAL_SERVER_ERROR),
    STOCK_NOT_DEFINED(1006, "Stock detail is not defined", INTERNAL_SERVER_ERROR);



    private int code;
    private String message;
    HttpStatus httpStatus;

}