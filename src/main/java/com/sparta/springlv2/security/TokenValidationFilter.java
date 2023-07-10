package com.sparta.springlv2.security;

import com.sparta.springlv2.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// WT 토큰의 유효성을 검사하는 필터입니다.
//  JWT 토큰이 올바르지 않거나 만료되었을 경우 인증 정보를 설정하지 않고 요청을 통과시킵니다. 
// UserService 클래스는 회원 가입과 로그인과 관련된 비즈니스 로직을 제공합니다. 
// 비밀번호 암호화, 사용자 인증, JWT 토큰 생성 등의 기능을 수행합니다.
public class TokenValidationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 토큰이 유효하면 인증 정보를 설정합니다.
            if (jwtUtil.validateToken(token, userDetails)) {
                // UserDetails를 사용하여 인증 정보를 설정합니다.
                // 여기서 UserDetails는 서비스에 따라 구현해야 합니다.
                // SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
