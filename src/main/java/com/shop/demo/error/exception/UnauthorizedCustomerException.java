package com.shop.demo.error.exception;

import com.shop.demo.error.ErrorCode;
import lombok.Getter;

@Getter
public class UnauthorizedCustomerException extends ServiceException {

    public UnauthorizedCustomerException(ErrorCode errorCode) {
        super(errorCode);
    }
}
