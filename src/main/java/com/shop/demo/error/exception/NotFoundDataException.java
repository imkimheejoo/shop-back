package com.shop.demo.error.exception;

import com.shop.demo.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundDataException extends ServiceException {

    public NotFoundDataException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotFoundDataException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
