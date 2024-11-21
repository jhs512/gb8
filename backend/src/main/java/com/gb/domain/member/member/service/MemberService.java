package com.gb.domain.member.member.service;

import com.gb.domain.member.member.entity.Member;
import com.gb.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public long count() {
        return memberRepository.count();
    }

    @Transactional
    public Member join(String username, String password, String nickname) {
        findByUsername(username).ifPresent(ignored -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .build();

        return memberRepository.save(member);
    }

    private Optional<Object> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}