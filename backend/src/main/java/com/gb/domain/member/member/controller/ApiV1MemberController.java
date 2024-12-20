package com.gb.domain.member.member.controller;

import com.gb.domain.global.exceptions.ServiceException;
import com.gb.domain.member.member.dto.AccessTokenMemberInfoDto;
import com.gb.domain.member.member.dto.MemberDto;
import com.gb.domain.member.member.entity.Member;
import com.gb.domain.member.member.service.MemberService;
import com.gb.global.rsData.RsData;
import com.gb.rq.Rq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "ApiV1MemberController", description = "MEMBER API 컨트롤러")
public class ApiV1MemberController {
    private final Rq rq;
    private final MemberService memberService;

    @AllArgsConstructor
    @Getter
    public static class MemberJoinReqBody {
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String nickname;
    }

    @PostMapping("/join")
    @Operation(summary = "회원가입")
    public RsData<MemberDto> join(
            @RequestBody @Valid MemberJoinReqBody reqBody
    ) {
        Member member = memberService.join(reqBody.username, reqBody.password, reqBody.nickname);

        return RsData
                .of(
                        "S-201-1",
                        "회원가입이 완료되었습니다.",
                        new MemberDto(member)
                );
    }


    @AllArgsConstructor
    @Getter
    public static class MemberLoginReqBody {
        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }

    @AllArgsConstructor
    @Getter
    public static class MemberLoginResBody {
        @NonNull
        private MemberDto item;

        @NonNull
        private String accessToken;
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public RsData<MemberLoginResBody> login(
            @RequestBody @Valid MemberLoginReqBody reqBody
    ) {
        Optional<Member> opMember = memberService.findByUsername(reqBody.username);

        Member member = opMember.orElseThrow(() -> new ServiceException("F-404-1", "존재하지 않는 회원입니다."));

        memberService.checkPassword(reqBody.password, member.getPassword());

        String accessToken = memberService.genAccessToken(member);

        return RsData
                .of(
                        "S-200-1",
                        "%s님 환영합니다.".formatted(member.getName()),
                        new MemberLoginResBody(new MemberDto(member), accessToken)
                );
    }


    @GetMapping("/me")
    @Operation(summary = "내 정보")
    public RsData<MemberDto> getMe() {
        String headerAuthorization = rq.getHeader("Authorization", "");

        if (headerAuthorization.isBlank()) {
            throw new ServiceException("F-401-1", "로그인이 필요합니다.");
        }

        String accessToken = headerAuthorization.replace("Bearer ", "");

        AccessTokenMemberInfoDto accessTokenMemberInfoDto = memberService.getMemberInfoFromAccessToken(accessToken);

        Optional<Member> opMember = memberService.findById(accessTokenMemberInfoDto.getId());

        Member member = opMember.orElseThrow(() -> new ServiceException("F-404-1", "존재하지 않는 회원입니다."));

        return RsData
                .of(
                        "S-200-1",
                        "%s님의 정보입니다.".formatted(member.getName()),
                        new MemberDto(member)
                );
    }
}
