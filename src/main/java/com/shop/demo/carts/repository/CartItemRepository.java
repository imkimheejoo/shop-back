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

    @EntityGraph(attributePaths = {"cart"})
    @Query("select ci from CartItem ci where ci.id = :cartItemId")
    Optional<CartItem> findWithCartById(@Param("cartItemId") Long cartItemId);
}
