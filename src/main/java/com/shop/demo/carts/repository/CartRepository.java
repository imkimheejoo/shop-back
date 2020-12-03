package com.shop.demo.carts.repository;

import com.shop.demo.carts.domain.Cart;
import com.shop.demo.dto.query.CartItemDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select new com.shop.demo.dto.query.CartItemDto" +
            "(ci.id, p.id, p.title, po.id, po.optionName, ci.info.count, p.imageUrl, po.optionPrice.money, p.price.money) from Cart c " +
            "inner join CartItem ci on c.id = ci.cart.id " +
            "inner join Product p on ci.info.productId = p.id " +
            "inner join ProductOption po on ci.info.productOptionId = po.id " +
            "where c.accountId = :accountId ")
    List<CartItemDto> findCartItemsByAccountId(@Param("accountId") Long accountId);

    @EntityGraph(attributePaths = {"cartItems"})
    Optional<Cart> findWithCartItemsByAccountId(@Param("accountId") Long accountId);

    Optional<Cart> findByAccountId(@Param("accountId") Long accountId);
}
