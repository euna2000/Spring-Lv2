package com.sparta.springlv2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글 작성 및 수정에 사용되는 요청 DTO 클래스입니다. title과 content 필드를 가지고 있습니다.
@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
}
