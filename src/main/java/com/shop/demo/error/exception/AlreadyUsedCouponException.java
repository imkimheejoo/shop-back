package com.shop.demo.error.exception;

import com.shop.demo.error.ErrorCode;
import lombok.Getter;

@Getter
public class AlreadyUsedCouponException extends ServiceException {

    public AlreadyUsedCouponException(ErrorCode errorCode) {
        super(errorCode);
    }

}
