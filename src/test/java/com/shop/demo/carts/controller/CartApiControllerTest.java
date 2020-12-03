package com.shop.demo.carts.controller;

import com.shop.demo.carts.domain.CartItemInfo;
import com.shop.demo.common.MockMvcTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CartApiControllerTest extends MockMvcTemplate {
    private static final String AUTHORIZATION = "Authorization";
    private static final String COMMON_URL = "/api/carts";
    private static final CartItemInfo cartItemInfo = new CartItemInfo(6L, 7L, 2);

    @Test
    @Transactional
    void 기존_장바구니에_상품추가() throws Exception {
        this.mockMvc.perform(post(COMMON_URL + "/item")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email@email.com", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemInfo)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void 새_장바구니에_상품추가() throws Exception {
        this.mockMvc.perform(post(COMMON_URL + "/item")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email2@email.com", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemInfo)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void 회원의_장바구니_조회() throws Exception {
        this.mockMvc.perform(get(COMMON_URL)
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email@email.com", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemInfo)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void 장바구니_아이템_삭제_성공() throws Exception {
        this.mockMvc.perform(delete(COMMON_URL + "/item/79")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email@email.com", "password")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void 장바구니_아이템_삭제_실패_다른사람의_장바구니상품_삭제() throws Exception {
        this.mockMvc.perform(delete(COMMON_URL+ "/item/79")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email2@email.com", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void 장바구니_아이템_삭제_실패_장바구니아이템_없음() throws Exception {
        this.mockMvc.perform(delete(COMMON_URL+ "/item/1")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email@email.com", "password")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void 장바구니_아이템_개수_변경() throws Exception {
        this.mockMvc.perform(patch(COMMON_URL+ "/item/79")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email@email.com", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(10)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void 장바구니_아이템_개수_변경_실패_개수_0이하() throws Exception {
        this.mockMvc.perform(patch(COMMON_URL+ "/item/79")
                .header(AUTHORIZATION, "Bearer " + getAccessToken("email@email.com", "password"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(0)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}