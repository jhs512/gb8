package com.gb.domain.member.member.service;

import com.gb.domain.global.exceptions.ServiceException;
import com.gb.domain.member.member.dto.AccessTokenMemberInfoDto;
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
    private final AuthTokenService authTokenService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public long count() {
        return memberRepository.count();
    }

    @Transactional
    public Member join(String username, String password, String nickname, String refreshToken) {
        if (refreshToken == null) refreshToken = authTokenService.genRefreshToken();

        findByUsername(username).ifPresent(ignored -> {
            throw new ServiceException("F-409-1", "이미 존재하는 회원입니다.");
        });

        Member member = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .refreshToken(refreshToken)
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public Member join(String username, String password, String nickname) {
        return join(username, password, nickname, authTokenService.genRefreshToken());
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public void checkPassword(String password, String passwordEncoded) {
        if (!passwordEncoder.matches(password, passwordEncoded)) {
            throw new ServiceException("F-401-1", "비밀번호가 일치하지 않습니다.");
        }
    }

    public String genAccessToken(Member member) {
        return authTokenService.genAccessToken(member);
    }

    public AccessTokenMemberInfoDto getMemberInfoFromAccessToken(String accessToken) {
        return authTokenService.getMemberInfoFromAccessToken(accessToken);
    }

    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }
}