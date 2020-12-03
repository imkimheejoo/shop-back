package com.shop.demo.products.service.query;

import com.shop.demo.dto.query.ProductDetailInfo;
import com.shop.demo.dto.query.ProductInfoDto;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotFoundDataException;
import com.shop.demo.products.Category;
import com.shop.demo.products.Product;
import com.shop.demo.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductRepository productRepository;

    public Page<ProductInfoDto> getRecentProducts(Pageable pageable) {
        // 쿼리 1번
        Page<Product> products = productRepository.findAll(pageable);

        // 쿼리 2번 (in 절)
        return products.map(ProductInfoDto::toDto);
    }

    public Page<ProductInfoDto> getProductsByCategory(String categoryName, Pageable pageable) {
        Category category = Category.getCategoryByName(categoryName);

        Page<Product> products = productRepository.findByCategory(category, pageable);

        return products.map(ProductInfoDto::toDto);
    }

    public Page<ProductInfoDto> getProductsContainsKeyword(String keyword, Pageable pageable) {
        Page<Product> products = productRepository.findProductsInfoInKeyword(keyword, pageable);

        return products.map(ProductInfoDto::toDto);
    }

    public ProductDetailInfo getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundDataException("상품을 찾을 수 없습니다.", ErrorCode.NOT_FOUND));

        return ProductDetailInfo.toDto(product);
    }
}
