package com.gb.domain.member.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private String createDate;

    @LastModifiedDate // UPDATE 발생날짜가 자동으로 저장됨
    @Setter(AccessLevel.NONE) // 외부에서 세터를 사용하지 못하도록
    private String modifyDate;

    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;
}