package com.shop.demo.carts.controller;

import com.shop.demo.MockMvcTemplate;
import com.shop.demo.carts.CartItem;
import com.shop.demo.carts.CartItemInfo;
import com.shop.demo.common.WithAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CartApiControllerTest extends MockMvcTemplate {

    private static final String COMMON_URL = "/api/carts";
    private static final CartItemInfo cartItemInfo = new CartItemInfo(6L, 7L, 2);

    @Test
    @WithAccount
    void 기존_장바구니에_상품추가() throws Exception {
        this.mockMvc.perform(post(COMMON_URL + "/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemInfo)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithAccount(id = 80L)
    void 새_장바구니에_상품추가() throws Exception {
        this.mockMvc.perform(post(COMMON_URL + "/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemInfo)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}