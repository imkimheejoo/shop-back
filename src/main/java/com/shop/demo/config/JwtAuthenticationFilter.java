package com.shop.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            Authentication authentication = jwtTokenProvider.getAuthentication((HttpServletRequest) request);
            if (authentication != null) {
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            InvalidTokenExceptionHandler(httpResponse);
        }
    }

    private void InvalidTokenExceptionHandler(HttpServletResponse response) throws IOException {
        final ErrorResponse error = ErrorResponse.of(ErrorCode.INVALID_TOKEN);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}
