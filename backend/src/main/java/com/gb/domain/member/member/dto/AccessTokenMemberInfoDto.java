package com.gb.domain.member.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AccessTokenMemberInfoDto {
    private long id;
    private String username;
    private List<String> authorities;
}
