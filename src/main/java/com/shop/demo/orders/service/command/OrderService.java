package com.shop.demo.orders.service.command;

import com.shop.demo.common.Money;
import com.shop.demo.coupons.AccountCoupon;
import com.shop.demo.coupons.repository.AccountCouponRepository;
import com.shop.demo.deliveries.Delivery;
import com.shop.demo.deliveries.repository.DeliveryRepository;
import com.shop.demo.dto.OrderInfo;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotFoundDataException;
import com.shop.demo.orders.Order;
import com.shop.demo.orders.OrderItem;
import com.shop.demo.orders.repository.OrderItemRepository;
import com.shop.demo.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DeliveryRepository deliveryRepository;
    private final AccountCouponRepository accountCouponRepository;

    public Long saveOrderProduct(OrderInfo orderInfo, Long ordererId) {
        Delivery delivery = deliveryRepository.findById(orderInfo.getDeliveryId())
                .orElseThrow(() -> new NotFoundDataException(ErrorCode.NOT_FOUND));


        Long couponId = orderInfo.getCouponId();
        if(couponId != null) {
            AccountCoupon coupon = accountCouponRepository.findById(couponId)
                    .orElseThrow(() -> new NotFoundDataException(ErrorCode.NOT_FOUND));

            // TODO: 2020/10/13  할인된 금액이 맞는지 확인해야함
            // 가격 확인 -> 사용자가 결제 누른 그 시간의 가격으로 저장 따라서 가격 비교할 필요 없음
        }

        Money totalPrice = new Money(orderInfo.getTotalPrice());
        Order order = Order.order(ordererId, delivery, couponId, totalPrice);

        List<OrderItem> orderItems = orderInfo.getOrderProducts().stream()
                .map(op -> new OrderItem(order, op))
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);

        return order.getId();
    }
}
