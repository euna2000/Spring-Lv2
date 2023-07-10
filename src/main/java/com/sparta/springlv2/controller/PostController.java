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

// 게시글 관련 API를 처리하는 역할을 합니다. 
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //  "/posts" 경로로 GET 요청이 오면 getAllPosts() 메서드가 실행되고, POST 요청이 오면 createPost() 메서드가 실행됩니다. 
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // createPost() 메서드는 요청 본문의 PostRequestDto를 받아와 게시글을 생성하고, JWT 토큰에서 사용자 정보를 추출하여 작성자로 설정합니다.
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(@Validated @RequestBody PostRequestDto requestDto, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        PostResponseDto createdPost = postService.createPost(requestDto, username);
        return ResponseEntity.ok(createdPost);
    }

    //  "/posts/{postId}" 경로로 GET 요청이 오면 getPostById() 메서드가 실행되고, PUT 요청이 오면 updatePost() 메서드가 실행되며, DELETE 요청이 오면 deletePost() 메서드가 실행됩니다.
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
