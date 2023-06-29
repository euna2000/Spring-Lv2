package com.sparta.springlv2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
}