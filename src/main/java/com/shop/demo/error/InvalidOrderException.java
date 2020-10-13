package com.shop.demo.error;

import com.shop.demo.error.exception.ServiceException;
import lombok.Getter;

@Getter
public class InvalidOrderException extends ServiceException {

    public InvalidOrderException(ErrorCode errorCode) {
        super(errorCode);
    }
}
