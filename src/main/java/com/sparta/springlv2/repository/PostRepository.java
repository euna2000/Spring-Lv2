package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 게시글 작업을 수행하기 위한 JpaRepository 인터페이스를 확장합니다.
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
}
