package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.CommentRequestDto;
import com.sparta.springlv2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// 댓글 관련 API를 처리하는 컨트롤러입니다. @RestController 어노테이션을 통해 RESTful API 컨트롤러임을 지정합니다.
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // createComment(): POST 메서드에 대한 "/comments" 경로로 요청이 오면 실행되는 메서드입니다.
    // 요청 본문의 CommentRequestDto를 받아와 댓글을 생성하고, JWT 토큰에서 사용자 정보를 추출하여 댓글 작성자로 설정합니다.
    @PostMapping("/comments")
    public ResponseEntity<String> createComment(@Validated @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        commentService.createComment(requestDto, username);
        return ResponseEntity.ok("댓글이 등록되었습니다.");
    }

    // updateComment(): PUT 메서드에 대한 "/comments/{commentId}" 경로로 요청이 오면 실행되는 메서드입니다. 
    // 경로 변수인 commentId와 요청 본문의 CommentRequestDto를 받아와 댓글을 수정하고, JWT 토큰에서 사용자 정보를 추출하여 댓글 작성자인지 확인합니다.
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @Validated @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        commentService.updateComment(commentId, requestDto, username);
        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }

    // deleteComment(): DELETE 메서드에 대한 "/comments/{commentId}" 경로로 요청이 오면 실행되는 메서드입니다. 
    // 경로 변수인 commentId를 받아와 댓글을 삭제하고JWT 토큰에서 사용자 정보를 추출하여 댓글 작성자인지 확인합니다.
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        commentService.deleteComment(commentId, username);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
