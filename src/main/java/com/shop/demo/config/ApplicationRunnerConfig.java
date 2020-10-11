package com.shop.demo.config;

import com.shop.demo.accounts.domain.Account;
import com.shop.demo.accounts.domain.AccountRole;
import com.shop.demo.accounts.dto.SignUpRequestDto;
import com.shop.demo.accounts.service.command.AccountService;
import com.shop.demo.common.Money;
import com.shop.demo.products.Category;
import com.shop.demo.products.Product;
import com.shop.demo.products.ProductOption;
import com.shop.demo.products.repository.ProductOptionRepository;
import com.shop.demo.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationRunnerConfig implements ApplicationRunner {

    private final AccountService accountService;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("email@email.com", "password", "김이름");
        Account account = accountService.register(signUpRequestDto, AccountRole.CUSTOMER);

        saveProducts();

    }

    private void saveProducts() {
        for (int i = 0; i < 100; i++) {
            Product product = Product.builder()
                    .title("상품" + i)
                    .description(i + "번째 상품")
                    .imageUrl("https://cdn.vuetifyjs.com/images/cards/sunshine.jpg")
                    .category(Category.상의)
                    .price(new Money(10000))
                    .build();


            ProductOption.builder()
                    .optionName("ivory")
                    .optionPrice(new Money(0))
                    .product(product)
                    .build();

            ProductOption.builder()
                    .optionName("black")
                    .optionPrice(new Money(0))
                    .product(product)
                    .build();

            productRepository.save(product);
        }
    }
}
