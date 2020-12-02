package com.shop.demo.config;

import com.shop.demo.accounts.domain.Account;
import com.shop.demo.accounts.domain.AccountRole;
import com.shop.demo.accounts.dto.SignUpRequestDto;
import com.shop.demo.accounts.service.command.AccountService;
import com.shop.demo.carts.Cart;
import com.shop.demo.carts.repository.CartRepository;
import com.shop.demo.common.Money;
import com.shop.demo.coupons.AccountCoupon;
import com.shop.demo.coupons.Coupon;
import com.shop.demo.coupons.repository.AccountCouponRepository;
import com.shop.demo.coupons.repository.CouponRepository;
import com.shop.demo.deliveries.Delivery;
import com.shop.demo.deliveries.repository.DeliveryRepository;
import com.shop.demo.products.Category;
import com.shop.demo.products.Product;
import com.shop.demo.products.ProductOption;
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
    private final DeliveryRepository deliveryRepository;
    private final CouponRepository couponRepository;
    private final AccountCouponRepository accountCouponRepository;
    private final CartRepository cartRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto("email@email.com", "password", "김이름");
        Account account = accountService.register(signUpRequestDto, AccountRole.CUSTOMER);
        Delivery delivery = deliveryRepository.save(new Delivery("12345", "서울특별시 강남구 신비아파트 1동 2호", account.getId()));

        Coupon coupon = couponRepository.save(new Coupon("12345-12", "2500원할인", new Money(2500)));
        accountCouponRepository.save(new AccountCoupon(account.getId(), coupon, false));
        accountCouponRepository.save(new AccountCoupon(account.getId(), coupon, true));

        saveProducts();
        Cart cart = cartRepository.save(new Cart(account));
        saveCartItem(cart);

    }

    private void saveCartItem(Cart cart) {

    }

    private void saveProducts() {
        for (int i = 0; i < 24; i++) {
            Product product = Product.builder()
                    .title("상품" + i)
                    .description(i + "번째 상품")
                    .imageUrl("https://cdn.vuetifyjs.com/images/cards/sunshine.jpg")
                    .category(Category.상의)
                    .price(new Money(10000))
                    .content("이상품은 어쩌구 저쩌구...")
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
