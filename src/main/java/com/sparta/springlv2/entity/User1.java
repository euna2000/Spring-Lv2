import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// User 클래스는 사용자 데이터를 표현하는 엔티티 클래스입니다.
@Entity
@Getter
// @NoArgsConstructor: 인자 없는 생성자를 자동으로 생성합니다.
@NoArgsConstructor
public class User extends Timestamped {

    // @Id: 해당 필드가 엔티티의 식별자(primary key)임을 표시합니다.
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY): 식별자 값을 자동으로 생성하기 위해 자동 증가 방식을 사용합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false): 해당 필드가 null이 아니어야 함을 표시합니다.
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    // public User(String username, String password): username과 password를 받아 사용자를 생성하는 생성자입니다.
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
