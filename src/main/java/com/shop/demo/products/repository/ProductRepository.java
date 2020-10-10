package com.shop.demo.products.repository;

import com.shop.demo.products.Product;
import com.shop.demo.products.service.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query(value = "select new com.shop.demo.products.service.dto.ProductDto(p.id, p.title, p.price.money, p.options, p.imageUrl) " +
//            "from Product p " +
//            "left outer join ProductOption o on p.id = o.product.id " +
//            "order by p.createdDate desc")
//    List<ProductDto> findAllWithSortLastDate(Pageable pageable);
}
