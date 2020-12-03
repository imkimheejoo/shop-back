package com.shop.demo.accounts.controller;

import com.shop.demo.common.MockMvcTemplate;
import com.shop.demo.accounts.dto.LoginRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountApiControllerTest extends MockMvcTemplate {
    private static final String COMMON_URL = "/api/accounts";

    @Transactional
    @Test
    void 일반고객_login_성공() throws Exception {
        mockMvc.perform(post(COMMON_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto("email@email.com", "password"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists());
    }

    @Transactional
    @Test
    void 일반고객_login_이메일_empty() throws Exception {
        mockMvc.perform(post(COMMON_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto("", "password"))))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Transactional
    @Test
    void 일반고객_login_실패_아이디_존재하지_않음() throws Exception {
        mockMvc.perform(post(COMMON_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto("exception@email.com", "password"))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Transactional
    @Test
    void 일반고객_login_실패_아이디_비밀번호_불일치() throws Exception {
        mockMvc.perform(post(COMMON_URL + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto("email@email.com", "exception"))))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser(username = "email@email.com", roles = "USER")
    void 로그아웃_성공() throws Exception {
        mockMvc.perform(logout())
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    void 로그인_없이_로그아웃() throws Exception {
        mockMvc.perform(logout())
                .andDo(print())
                .andExpect(unauthenticated());
    }
}