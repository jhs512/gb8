package com.gb.domain.member.member.controller;

import com.gb.domain.member.member.dto.MemberDto;
import com.gb.domain.member.member.entity.Member;
import com.gb.domain.member.member.service.MemberService;
import com.gb.global.rsData.RsData;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;

    @AllArgsConstructor
    public static class MemberJoinReqBody {
        private String username;
        private String password;
        private String nickname;
    }

    @PostMapping("/join")
    public RsData<MemberDto> join(
            @RequestBody MemberJoinReqBody reqBody
    ) {
        Member member = memberService.join(reqBody.username, reqBody.password, reqBody.nickname);

        return RsData
                .of(
                        "S-1",
                        "회원가입이 완료되었습니다.",
                        new MemberDto(member)
                );
    }
}
