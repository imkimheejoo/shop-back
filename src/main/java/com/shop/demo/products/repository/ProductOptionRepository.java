package com.shop.demo.products.repository;

import com.shop.demo.dto.query.OptionNameDto;
import com.shop.demo.products.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    @Query("select new com.shop.demo.dto.query.OptionNameDto(po.product.id, po.optionName) from ProductOption po where po.product.id in (:productIds)")
    List<OptionNameDto> findOptionNameInProducts(@Param("productIds") List<Long> productIds);
}
