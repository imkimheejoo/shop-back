package com.shop.demo.domain.orders;

import lombok.Getter;

@Getter
public enum ShippingStatus {
    배송전, 배송준비중, 배송중, 배송완료
}
