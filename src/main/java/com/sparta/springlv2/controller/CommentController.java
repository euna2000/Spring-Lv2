package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.CommentRequestDto;
import com.sparta.springlv2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<String> createComment(@Validated @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        commentService.createComment(requestDto, username);
        return ResponseEntity.ok("댓글이 등록되었습니다.");
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @Validated @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        commentService.updateComment(commentId, requestDto, username);
        return ResponseEntity.ok("댓글이 수정되었습니다.");
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String token) {
        String username = parseToken(token);
        commentService.deleteComment(commentId, username);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
