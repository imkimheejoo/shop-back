package com.shop.demo.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C-001", "잘못된 입력형식입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C-002", "지원하지 않는 요청입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "C-003", "유효하지 않은 인증토큰 입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C-004", "엔티티를 찾을 수 없습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "C-005", "권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-006", "예기치 못한 문제가 발생했습니다. 다시한번 시도해주세요." +
            "\n계속 작동이 안된다면 문의바랍니다.");

    private HttpStatus status;
    private String code;
    private String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
