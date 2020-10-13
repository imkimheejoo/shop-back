package com.shop.demo.orders.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.demo.MockMvcTemplate;
import com.shop.demo.accounts.dto.LoginRequestDto;
import com.shop.demo.common.ItemInfo;
import com.shop.demo.common.Money;
import com.shop.demo.dto.OrderInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApiControllerTest extends MockMvcTemplate {

    private static final String COMMON_URL = "/api/orders";

    @Test
    @Transactional
    @Rollback(false)
    void order() throws Exception {
        List<ItemInfo> orderProducts = Arrays.asList(new ItemInfo(3L, 4L, 1, new Money(0L)),
                new ItemInfo(3L, 5L, 1, new Money(0L)));
        OrderInfo orderInfo = new OrderInfo(orderProducts, 2L, null, 10000L);

        mockMvc.perform(post(COMMON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderInfo)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").isNotEmpty());
    }
}