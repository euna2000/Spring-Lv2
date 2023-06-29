// PostController.java
package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(@Validated @RequestBody PostRequestDto requestDto, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        PostResponseDto createdPost = postService.createPost(requestDto, username);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId) {
        PostResponseDto post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @Validated```java
            @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @Validated @RequestBody PostRequestDto requestDto, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        PostResponseDto updatedPost = postService.updatePost(postId, requestDto, username);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        postService.deletePost(postId, username);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }

    private String parseToken(String token) {
        // 토큰 파싱 로직 구현
        // 토큰에서 사용자 정보(username) 추출하여 반환
        return username;
    }
}
