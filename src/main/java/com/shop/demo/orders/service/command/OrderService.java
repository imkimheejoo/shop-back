package com.shop.demo.orders.service.command;

import com.shop.demo.common.Money;
import com.shop.demo.coupons.AccountCoupon;
import com.shop.demo.coupons.repository.AccountCouponRepository;
import com.shop.demo.deliveries.Delivery;
import com.shop.demo.deliveries.repository.DeliveryRepository;
import com.shop.demo.dto.OrderInfo;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.InvalidOrderException;
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
        checkValidOrder(orderInfo);

        Delivery delivery = deliveryRepository.findById(orderInfo.getDeliveryId())
                .orElseThrow(() -> new NotFoundDataException(ErrorCode.NOT_FOUND));

        Money totalPrice = orderInfo.getTotalPrice();
        Order order = Order.order(ordererId, delivery, orderInfo.getCouponId(), totalPrice);

        List<OrderItem> orderItems = orderInfo.getOrderProducts().stream()
                .map(op -> new OrderItem(order, op))
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);

        return order.getId();
    }

    private void checkValidOrder(OrderInfo orderInfo) {
        Long couponId = orderInfo.getCouponId();
        if(couponId != null) {
            AccountCoupon coupon = accountCouponRepository.findByIdWithCoupons(couponId)
                    .orElseThrow(() -> new NotFoundDataException(ErrorCode.NOT_FOUND));

            coupon.validate(orderInfo.getOrderProducts(), orderInfo.getTotalPrice());
        } else {
            Money origin = new Money(orderInfo.getOrderProducts().stream()
                    .mapToLong(op -> op.getPrice().getMoney())
                    .sum());

            if(!origin.equals(orderInfo.getTotalPrice())) {
                throw new InvalidOrderException(ErrorCode.INVALID_ORDER);
            }
        }
    }
}
