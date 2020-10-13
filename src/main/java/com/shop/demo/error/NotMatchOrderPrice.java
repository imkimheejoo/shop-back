package com.shop.demo.error;

import com.shop.demo.error.exception.ServiceException;
import lombok.Getter;

@Getter
public class NotMatchOrderPrice extends ServiceException {

    public NotMatchOrderPrice(ErrorCode errorCode) {
        super(errorCode);
    }
}
