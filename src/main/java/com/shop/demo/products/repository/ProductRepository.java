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

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @EntityGraph(attributePaths = {"options"})
    Page<Product> findAll(Pageable pageable);

    @Query(value = "select new com.shop.demo.dto.query.ProductInfoDto(p.id, p.title, p.price.money, p.imageUrl) " +
            "from Product p")
    Page<ProductInfoDto> findProductsInfo(Pageable pageable);

    @Query(value = "select new com.shop.demo.dto.query.ProductInfoDto(p.id, p.title, p.price.money, p.imageUrl) " +
            "from Product p " +
            "where p.category = :category")
    Page<ProductInfoDto> findProductsInfoByCategory(@Param("category") Category category, Pageable pageable);
}
