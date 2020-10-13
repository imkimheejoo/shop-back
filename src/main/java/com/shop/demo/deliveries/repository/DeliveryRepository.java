package com.shop.demo.deliveries.repository;

import com.shop.demo.deliveries.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
