package com.shop.demo.products.service.query;

import com.shop.demo.dto.query.OptionNameDto;
import com.shop.demo.dto.query.ProductDetailInfo;
import com.shop.demo.dto.query.ProductInfoDto;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.NotFoundDataException;
import com.shop.demo.products.Category;
import com.shop.demo.products.Product;
import com.shop.demo.products.repository.ProductOptionRepository;
import com.shop.demo.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;


    // 방법 1 DTO로 조회하고 반환
    public Page<ProductInfoDto> getRecentProductsV1(Pageable pageable) {
        // 쿼리 2번
        Page<ProductInfoDto> products = productRepository.findProductsInfo(pageable);   // 1번째 쿼리
        getProductOptions(products);

        return products;
    }

    private void getProductOptions(Page<ProductInfoDto> products) {
        List<ProductInfoDto> content = products.getContent();

        List<Long> productIds = content.stream()
                .map(ProductInfoDto::getId)
                .collect(Collectors.toList());

        // 2번째 쿼리
        Map<Long, List<OptionNameDto>> options = productOptionRepository.findOptionNameInProducts(productIds).stream()
                .collect(Collectors.groupingBy(OptionNameDto::getProductId));

        content.forEach(c -> c.setOptions(options.get(c.getId())));
    }

    // 방법 2 엔티티로 조회하고 DTO로 변환 -> oneTomany는 페이징 안됨(MultiBegException)
    public Page<ProductInfoDto> getRecentProducts(Pageable pageable) {
        // 쿼리 1번
        Page<Product> products = productRepository.findProductsInfo2(pageable);

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
