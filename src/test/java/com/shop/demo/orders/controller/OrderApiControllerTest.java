package com.shop.demo.orders.controller;

import com.shop.demo.MockMvcTemplate;
import com.shop.demo.common.ItemInfo;
import com.shop.demo.common.Money;
import com.shop.demo.dto.OrderInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApiControllerTest extends MockMvcTemplate {

    private static final String COMMON_URL = "/api/orders";

    List<ItemInfo> orderProducts = Arrays.asList(new ItemInfo(6L, 7L, 1, new Money(10000L)),
            new ItemInfo(6L, 8L, 1, new Money(10000L)));

    @Test
    @Transactional
    void order성공_쿠폰없는경우() throws Exception {
        OrderInfo orderInfo = new OrderInfo(orderProducts, 2L, null, new Money(20000L));

        mockMvc.perform(post(COMMON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderInfo)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").isNotEmpty());
    }

    @Test
    @Transactional
    void order실패_쿠폰없는경우_실제_할인된금액과_입력으로온금액_불일치() throws Exception {
        OrderInfo orderInfo = new OrderInfo(orderProducts, 2L, null, new Money(999L));

        mockMvc.perform(post(COMMON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderInfo)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("O-001"));
    }

    @Test
    @Transactional
    void order실패_쿠폰있는경우_성공() throws Exception {
        OrderInfo orderInfo = new OrderInfo(orderProducts, 2L, 4L, new Money(17500L));

        mockMvc.perform(post(COMMON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderInfo)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").isNotEmpty());
    }

    @Test
    @Transactional
    void order실패_쿠폰있는경우_실패_이미_사용한_쿠폰() throws Exception {
        OrderInfo orderInfo = new OrderInfo(orderProducts, 2L, 5L, new Money(17500L));

        mockMvc.perform(post(COMMON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderInfo)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("CU-001"));
    }

    @Test
    @Transactional
    void order실패_쿠폰있는경우_실패_할인된_금액과_실제금액이_다름() throws Exception {
        List<ItemInfo> orderProducts = Arrays.asList(new ItemInfo(3L, 4L, 1, new Money(0L)),
                new ItemInfo(3L, 5L, 1, new Money(0L)));
        OrderInfo orderInfo = new OrderInfo(orderProducts, 2L, 4L, new Money(123L));

        mockMvc.perform(post(COMMON_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderInfo)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("CU-002"));
    }

}