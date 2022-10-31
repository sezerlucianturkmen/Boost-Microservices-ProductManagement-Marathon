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

    PRODUCT_DUPLICATE(1003, "This product is exist", INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_FOUND(1004, "The product is not found", INTERNAL_SERVER_ERROR),
    PRODUCT_NOT_CREATED(1005, "The product is not saved", INTERNAL_SERVER_ERROR);



    private int code;
    private String message;
    HttpStatus httpStatus;

}