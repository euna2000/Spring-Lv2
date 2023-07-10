package com.sparta.springlv2.service;

import com.sparta.springlv2.dto.PostRequestDto;
import com.sparta.springlv2.dto.PostResponseDto;
import com.sparta.springlv2.dto.SignupRequestDto;
import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.repository.PostRepository;
import com.sparta.springlv2.repository.UserRepository;
import com.sparta.springlv2.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// PostService 클래스는 게시글과 관련된 비즈니스 로직을 제공합니다. 
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        return posts.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto, String username) {
        User user = userRepository.findByUsername(username);

        Post post = new Post(requestDto.getTitle(), requestDto.getContent(), LocalDateTime.now());
        post.setUser(user);
        postRepository.save(post);

        return convertToResponseDto(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재합니다. 주어진 코드는 요구사항을 충족하고 기능적으로 동작할 것으로 보입니다. 단, 수정을 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인하는 부분은 주석 처리하였습니다. 대신, 토큰 검사를 통해 해당 사용자가 작성한 게시글만 수정 가능하도록 구현하였습니다.

                        또한, 회원 가입 API와 로그인 API의 요구사항에 맞게 수정되었습니다.

                        위의 코드에서 아래의 코드로 수정되었습니다.

                        ```java
// UserController.java
package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.SignupRequestDto;
import com.sparta.springlv2.dto.TokenDto;
import com.sparta.springlv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

        @RestController
        @RequiredArgsConstructor
        public class UserController {

            private final UserService userService;

            @PostMapping("/signup")
            public ResponseEntity<String> signup(@Validated @RequestBody SignupRequestDto requestDto) {
                if (userService.isUsernameExists(requestDto.getUsername())) {
                    return new ResponseEntity<>("이미 존재하는 username입니다.", HttpStatus.BAD_REQUEST);
                }

                userService.signup(requestDto);
                return ResponseEntity.ok("회원 가입에 성공했습니다.");
            }

            @PostMapping("/login")
            public ResponseEntity<TokenDto> login(@Validated @RequestBody SignupRequestDto requestDto) {
                if (!userService.isUsernameExists(requestDto.getUsername())) {
                    return new ResponseEntity<>("존재하지 않는 username입니다.", HttpStatus.BAD_REQUEST);
                }

                if (!userService.authenticateUser(requestDto)) {
                    return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
                }

                String token = userService.login(requestDto);
                TokenDto tokenDto = new TokenDto(token);
                return ResponseEntity.ok(tokenDto);
            }
        }
