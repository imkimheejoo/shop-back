package com.shop.demo.dto;

import com.shop.demo.common.ItemInfo;
import com.shop.demo.common.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {

    @NotEmpty
    private List<ItemInfo> orderProducts;

    @NotEmpty
    private Long deliveryId;

    private Long couponId;

    @NotEmpty
    private Money totalPrice;

}
