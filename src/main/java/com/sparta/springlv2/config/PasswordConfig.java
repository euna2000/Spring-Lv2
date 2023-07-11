package com.sparta.springlv2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 교차 리소스 공유 설정을 위한 클래스입니다. 
// 다른 도메인에서의 요청을 허용하기 위해 addCorsMappings() 메서드를 오버라이딩하여 CORS 구성을 설정합니다. 
// 모든 경로(/**)에 대해 모든 오리진(allowedOrigins("*")), 모든 HTTP 메서드(allowedMethods("*")), 
// 모든 헤더(allowedHeaders("*"))를 허용하도록 설정하며, 자격 증명(cookie, HTTP 인증 등)을 허용하고 1시간 동안 유효한 응답을 캐시합니다.
@Configuration
@EnableWebMvc
public class PasswordConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600L);
    }
}
