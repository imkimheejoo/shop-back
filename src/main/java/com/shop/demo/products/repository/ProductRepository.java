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

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select new com.shop.demo.dto.query.ProductInfoDto(p.id, p.title, p.price.money, p.imageUrl) " +
            "from Product p")
    Page<ProductInfoDto> findProductsInfo(Pageable pageable);

    @Query(value = "select new com.shop.demo.dto.query.ProductInfoDto(p.id, p.title, p.price.money, p.imageUrl) " +
            "from Product p " +
            "where p.category = :category")
    Page<ProductInfoDto> findProductsInfoByCategory(@Param("category") Category category, Pageable pageable);

    @Query(value = "select new com.shop.demo.dto.query.ProductInfoDto(p.id, p.title, p.price.money, p.imageUrl) " +
            "from Product p " +
            "where p.title like %:keyword%")
    Page<ProductInfoDto> findProductsInfoInKeyword(@Param(("keyword")) String keyword, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"options"})
    Optional<Product> findById(Long productId);

    //distinct 는 한 줄이 완전히 똑같을 때 만 날라감 , id만 같을 경우에는 안됨 근데 JPA는 해줌(어플리케이션 단에서!, 디비단 아님!)
    @Query("select p from Product p")
    Page<Product> findProductsInfo2(Pageable pageable);
}
