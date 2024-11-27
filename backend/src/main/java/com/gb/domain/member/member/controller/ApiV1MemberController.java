package com.gb.domain.member.member.controller;

import com.gb.domain.member.member.dto.MemberDto;
import com.gb.domain.member.member.entity.Member;
import com.gb.domain.member.member.service.MemberService;
import com.gb.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "ApiV1MemberController", description = "MEMBER API 컨트롤러")
public class ApiV1MemberController {
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
                        "S-1",
                        "회원가입이 완료되었습니다.",
                        new MemberDto(member)
                );
    }
}
