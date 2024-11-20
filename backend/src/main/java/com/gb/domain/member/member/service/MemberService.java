package com.gb.domain.member.member.service;

import com.gb.domain.member.member.entity.Member;
import com.gb.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(String username, String password, String nickname) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        return memberRepository.save(member);
    }
}