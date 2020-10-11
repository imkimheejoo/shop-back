package com.shop.demo.products.controller;

import com.shop.demo.products.service.query.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductQueryService productQueryService;

    @GetMapping
    public ResponseEntity getRecentProducts(@PageableDefault(size = 12, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity(productQueryService.getRecentProducts(pageable), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getProductsByCategory(@PathVariable String category, @PageableDefault(size = 12, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity(productQueryService.getProductsByCategory(category, pageable), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity getProductsByKeyword(@PathVariable String keyword, @PageableDefault(size = 12, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity(productQueryService.getProductsContainsKeyword(keyword, pageable), HttpStatus.OK);
    }

}
