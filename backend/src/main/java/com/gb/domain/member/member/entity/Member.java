package com.gb.domain.member.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) // @CreatedDate, @LastModifiedDate 를 작동시키기 위한 코드
public class Member {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @CreatedDate // INSERT 발생날짜가 자동으로 저장됨
    @Setter(AccessLevel.NONE) // 외부에서 세터를 사용하지 못하도록
    private LocalDateTime createDate;

    @LastModifiedDate // UPDATE 발생날짜가 자동으로 저장됨
    @Setter(AccessLevel.NONE) // 외부에서 세터를 사용하지 못하도록
    private LocalDateTime modifyDate;

    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private String refreshToken;

    public String getName() {
        return this.nickname;
    }

    public List<String> getAuthoritiesAsStringList() {
        List<String> authorities = new ArrayList<>();

        authorities.add("ROLE_MEMBER");

        if (isAdmin())
            authorities.add("ROLE_ADMIN");

        return authorities;
    }

    private boolean isAdmin() {
        return username.equals("admin");
    }
}