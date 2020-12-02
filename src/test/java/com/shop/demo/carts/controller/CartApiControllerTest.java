package com.shop.demo.carts.controller;

import com.shop.demo.MockMvcTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CartApiControllerTest extends MockMvcTemplate {

    private static final String COMMON_URL = "/api/carts";

    @Test
    @WithMockUser(username = "email@email.com", roles = "USER")
    void getMyCart() throws Exception {
        this.mockMvc.perform(get(COMMON_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }
}