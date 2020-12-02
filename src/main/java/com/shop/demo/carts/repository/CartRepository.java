package com.shop.demo.carts.repository;

import com.shop.demo.carts.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @EntityGraph(attributePaths = {"cartItems"})
    @Query("select c from Cart c where c.account.id = :accountId ")
    Optional<Cart> findByAccountId(@Param("accountId") Long accountId);
}
