package com.shop.demo.error.exception;

import com.shop.demo.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidPriceException extends ServiceException {

    public InvalidPriceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
