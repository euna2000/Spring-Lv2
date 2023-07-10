package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 사용자 엔티티와 관련된 데이터베이스 작업을 수행하기 위한 JpaRepository 인터페이스를 확장합니다.
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
