package com.shop.demo.error.exception;

import com.shop.demo.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTokenException extends ServiceException {

    public InvalidTokenException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
