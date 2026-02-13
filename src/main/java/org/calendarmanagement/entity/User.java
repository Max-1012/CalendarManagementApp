package org.calendarmanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Setter
    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @Column(nullable=false,length = 100)    // 암호화된 비밀번호(해시화? 암호화? 될 수 있어서 length 넉넉하게 설정)
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}
