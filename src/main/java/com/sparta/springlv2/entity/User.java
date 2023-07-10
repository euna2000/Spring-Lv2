package com.sparta.springlv2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//사용자 데이터를 표현하는 엔티티입니다. Post 엔티티와 1:N 관계를 맺고 있습니다.
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY): 식별자 값을 자동으로 생성하기 위해 자동 증가 방식을 사용합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(unique = true, nullable = false): 해당 필드가 유일해야 하고, null이 아니어야 함을 표시합니다.
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL): 1:N 관계를 표시합니다.
    // User 엔티티와 Post 엔티티 사이의 관계를 맺고 있으며, mappedBy 속성을 사용하여 Post 엔티티의 user 필드에 매핑된다는 것을 나타냅니다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // List<Post> posts = new ArrayList<>(): User 엔티티와 관련된 모든 Post 엔티티를 저장하기 위한 리스트입니다.
    private List<Post> posts = new ArrayList<>();

    // public User(String username, String password): username과 password를 받아 사용자를 생성하는 생성자입니다.
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
