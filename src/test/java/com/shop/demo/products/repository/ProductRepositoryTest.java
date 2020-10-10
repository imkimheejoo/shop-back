package com.shop.demo.products.repository;

import com.shop.demo.products.service.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAllWithSortLastDate() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<ProductDto> productDtos = productRepository.findAllWithSortLastDate(pageRequest);

        System.out.println(productDtos.get(0).getId());
        System.out.println(productDtos.get(0).getTitle());
        System.out.println(productDtos.get(0).getPrice());
        System.out.println(productDtos.get(0).getThumbnailUrl());
        System.out.println(productDtos.get(0).getOptions());
//        assertEquals(productDtos.get(0).getId(), );
    }
}