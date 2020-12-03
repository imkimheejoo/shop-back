package com.shop.demo.products.controller;

import com.shop.demo.common.MockMvcTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApiControllerTest extends MockMvcTemplate {

    private static final String COMMON_URL = "/api/products";

    @Test
    void 최근_상품들_조회() throws Exception {
        mockMvc.perform(get(COMMON_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".content").exists())
                .andExpect(jsonPath(".content").isNotEmpty());
    }

    @Test
    void 카테고리별_상품_조회() throws Exception {
        mockMvc.perform(get(COMMON_URL + "/category/top"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".content").exists())
                .andExpect(jsonPath(".content").isNotEmpty());
    }

    @Test
    void 카테고리별_상품_조회_실패_없는_카테고리() throws Exception {
        mockMvc.perform(get(COMMON_URL + "/category/exception"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(".message").value("잘못된 입력형식입니다."));
    }

    @Test
    void 상품_검색_한건이상() throws Exception {
        mockMvc.perform(get(COMMON_URL + "/search/상품"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".content").exists())
                .andExpect(jsonPath(".content").isNotEmpty());
    }

    @Test
    void 상품_검색_없는_싱품() throws Exception {
        mockMvc.perform(get(COMMON_URL + "/search/아무키워드"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".content").exists())
                .andExpect(jsonPath(".content.size()").value(0));
    }

    @Test
    void 상품_조회_성공() throws Exception {
        mockMvc.perform(get(COMMON_URL + "/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".content").exists())
                .andExpect(jsonPath(".content").isNotEmpty());
    }

    @Test
    void 상품_조회_실패_없는상품() throws Exception {
        mockMvc.perform(get(COMMON_URL + "/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(".message").value("엔티티를 찾을 수 없습니다."));
    }
}