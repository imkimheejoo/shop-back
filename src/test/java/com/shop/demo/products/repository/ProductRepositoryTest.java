package com.shop.demo.products.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

//    @Test
//    void findProductInfo() {
//        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by(Sort.Direction.DESC, "createdDate"));
//        Page<ProductDto> productDtos = productRepository.findProductInfo(pageRequest);
//
//        List<ProductDto> content = productDtos.getContent();
//        System.out.println("size: " + content.size());
////        System.out.println(productDtos.get(0).getTitle());
////        System.out.println(productDtos.get(0).getPrice());
////        System.out.println(productDtos.get(0).getImageUrl());
////        System.out.println(productDtos.get(0).getOptions());
//    }
}