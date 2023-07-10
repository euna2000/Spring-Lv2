package com.sparta.springlv2.config;

import com.sparta.springlv2.security.TokenValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring Security를 설정하는 클래스입니다. WebSecurityConfigurerAdapter를 상속받아 필요한 메서드를 오버라이딩하여 웹 보안 구성을 설정합니다.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // configure(HttpSecurity http): HTTP 보안 구성을 설정합니다.
    // CSRF(Cross-Site Request Forgery)를 비활성화하고, "/signup", "/login" 경로에 대한 요청은 인증 없이 허용하며, 나머지 모든 요청은 인증을 필요로 합니다. 
    // 세션 관리 정책을 STATELESS로 설정하여 세션을 사용하지 않도록 합니다. 
    // TokenValidationFilter를 UsernamePasswordAuthenticationFilter 앞에 추가하여 JWT 토큰의 유효성을 검사하도록 설정합니다.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new TokenValidationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // configure(AuthenticationManagerBuilder auth): 인증 매니저의 구성을 설정합니다. 
    // userDetailsService를 사용하여 사용자 인증을 처리하고, passwordEncoder를 사용하여 비밀번호 인코딩을 설정합니다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    // authenticationManagerBean(): 인증 매니저를 빈으로 등록합니다.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
