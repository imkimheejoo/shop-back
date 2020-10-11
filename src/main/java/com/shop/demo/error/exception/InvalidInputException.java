package com.shop.demo.error.exception;

import com.shop.demo.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidInputException extends ServiceException {

    public InvalidInputException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidInputException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
