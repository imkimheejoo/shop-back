package com.shop.demo.error;

import com.shop.demo.error.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException e) {
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        log.error("serviceException: {}", e.getErrorCode());
        return new ResponseEntity<>(response, response.getStatus());
    }

    // TODO: checkedException
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AuthenticationException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.ACCESS_DENIED);
        log.error("accessDeniedException: {}", response);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE, e.getBindingResult());
        log.error("argumentNotValidException: {}", response);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        log.error("methodNotAllowedException: {}", response);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleServerException(Exception exception) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        log.error("serverException: {}", response);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
