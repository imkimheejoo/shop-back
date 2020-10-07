package com.shop.demo.accounts.controller;

import com.shop.demo.MockMvcTemplate;
import com.shop.demo.accounts.dto.LoginRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountApiControllerTest extends MockMvcTemplate {

    @Transactional
    @Test
    void 일반고객_login_성공() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto("email@email.com", "password"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }

    @Transactional
    @Test
    void 일반고객_login_실패_아이디_존재하지_않음() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto("exception@email.com", "password"))))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Transactional
    @Test
    void 일반고객_login_실패_아이디_비밀번호_불일치() throws Exception {
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto("email@email.com", "exception"))))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}