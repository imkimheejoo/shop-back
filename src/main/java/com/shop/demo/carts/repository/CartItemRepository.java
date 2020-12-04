package com.shop.demo.carts.repository;

import com.shop.demo.carts.domain.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select ci from CartItem ci where ci.id = :cartItemId and ci.cart.accountId = :accountId")
    Optional<CartItem> findByIdAndAccountId(@Param("cartItemId") Long cartItemId, @Param("accountId") Long accountId);
}
