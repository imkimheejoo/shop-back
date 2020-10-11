package com.shop.demo.products.service.query;

import com.shop.demo.dto.query.OptionNameDto;
import com.shop.demo.dto.query.ProductInfoDto;
import com.shop.demo.products.Category;
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
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    @Transactional(readOnly = true)
    public Page<ProductInfoDto> getRecentProducts(Pageable pageable) {
        Page<ProductInfoDto> products = productRepository.findProductsInfo(pageable);
        getProductOptions(products);

        return products;
    }

    public Page<ProductInfoDto> getProductsByCategory(String categoryName, Pageable pageable) {
        Category category = Category.getCategoryByName(categoryName);

        Page<ProductInfoDto> products = productRepository.findProductsInfoByCategory(category, pageable);
        getProductOptions(products);

        return products;
    }

    public Page<ProductInfoDto> getProductsContainsKeyword(String keyword, Pageable pageable) {
        Page<ProductInfoDto> products = productRepository.findProductsInfoInKeyword(keyword, pageable);

        getProductOptions(products);

        return products;
    }

    private void getProductOptions(Page<ProductInfoDto> products) {
        List<ProductInfoDto> content = products.getContent();

        List<Long> productIds = content.stream()
                .map(ProductInfoDto::getId)
                .collect(Collectors.toList());

        Map<Long, List<OptionNameDto>> options = productOptionRepository.findOptionNameInProducts(productIds).stream()
                .collect(Collectors.groupingBy(OptionNameDto::getProductId));

        content.forEach(c -> c.setOptions(options.get(c.getId())));
    }
}
