package com.shop.demo.products.repository;

import com.shop.demo.dto.query.ProductInfoDto;
import com.shop.demo.products.Category;
import com.shop.demo.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategory(@Param("category") Category category, Pageable pageable);

    @Query(value = "select p " +
            "from Product p " +
            "where p.title like %:keyword%")
    Page<Product> findProductsInfoInKeyword(@Param(("keyword")) String keyword, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"options"})
    Optional<Product> findById(Long productId);
}
