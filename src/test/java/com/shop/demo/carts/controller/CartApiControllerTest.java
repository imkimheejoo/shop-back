package com.shop.demo.carts.controller;

import com.shop.demo.carts.CartItemInfo;
import com.shop.demo.common.MockMvcTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CartApiControllerTest extends MockMvcTemplate {
    private static final String AUTHORIZATION = "Authorization";
    private static final String COMMON_URL = "/api/carts";
    private static final CartItemInfo cartItemInfo = new CartItemInfo(6L, 7L, 2);

    @Test
    @Rollback
    void 기존_장바구니에_상품추가() throws Exception {
        this.mockMvc.perform(post(COMMON_URL + "/item")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email@email.com", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemInfo)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Rollback
    void 새_장바구니에_상품추가() throws Exception {
        this.mockMvc.perform(post(COMMON_URL + "/item")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email2@email.com", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemInfo)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}