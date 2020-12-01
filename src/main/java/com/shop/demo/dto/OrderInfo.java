package com.shop.demo.dto;

import com.shop.demo.common.ItemInfo;
import com.shop.demo.common.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {

    @NotNull
    private List<ItemInfo> orderProducts;

    @NotNull
    private Long deliveryId;

    private Long couponId;

    @NotNull
    private Money totalPrice;

}
