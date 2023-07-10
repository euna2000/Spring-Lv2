package com.sparta.springlv2.controller;

import com.sparta.springlv2.exception.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// API 예외 처리를 담당하는 컨트롤러입니다. @RestControllerAdvice 어노테이션을 통해 전역 예외 처리를 정의합니다.
@RestControllerAdvice
public class ExceptionController {

    // handleApiRequestException(): ApiRequestException 예외가 발생했을 때 실행되는 메서드입니다. 
    // 예외 메시지를 담은 ExceptionResponseDto를 생성하고, 예외의 HTTP 상태 코드를 기반으로 응답을 생성하여 반환합니다.
    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<String> handleApiRequestException(ApiRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
