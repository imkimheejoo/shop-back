package com.shop.demo.products.controller;

import com.shop.demo.MockMvcTemplate;
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
        mockMvc.perform(get(COMMON_URL + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(".contents").exists())
                .andExpect(jsonPath(".contents").isNotEmpty());
    }
}