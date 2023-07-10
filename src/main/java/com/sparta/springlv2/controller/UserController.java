package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.LoginRequestDto;
import com.sparta.springlv2.dto.SignupRequestDto;
import com.sparta.springlv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// 회원 가입 및 로그인과 관련된 API를 처리하는 역할을 합니다.
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //  "/signup" 경로로 POST 요청이 오면 signup() 메서드가 실행되어 회원 가입을 처리하고, "/login" 경로로 POST 요청이 오면 login() 메서드가 실행되어 로그인을 처리합니다.
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Validated @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원 가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequestDto requestDto) {
        String token = userService.login(requestDto);
        return ResponseEntity.ok(token);
    }
}
