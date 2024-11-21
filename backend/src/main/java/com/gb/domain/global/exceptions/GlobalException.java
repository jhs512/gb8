package com.gb.domain.global.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private final String resultCode;
    private final String msg;
}
