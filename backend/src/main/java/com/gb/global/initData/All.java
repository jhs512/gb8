package com.gb.global.initData;

import com.gb.domain.member.member.service.MemberService;
import com.gb.global.app.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class All {
    private final MemberService memberService;

    @Bean
    public ApplicationRunner initDataAll() {
        return args -> {
            if (memberService.count() > 0) return;

            memberService.join("system", "1234", "시스템", AppConfig.isNotProd() ? "system" : null);
            memberService.join("admin", "1234", "관리자", AppConfig.isNotProd() ? "admin" : null);
            memberService.join("user1", "1234", "회원_1", AppConfig.isNotProd() ? "user1" : null);
            memberService.join("user2", "1234", "회원_2", AppConfig.isNotProd() ? "user2" : null);
        };
    }
}