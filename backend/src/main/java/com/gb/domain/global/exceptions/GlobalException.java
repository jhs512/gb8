package com.gb.domain.global.exceptions;

import com.gb.global.rsData.RsData;
import com.gb.standard.base.Empty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private final String resultCode;
    private final String msg;

    public RsData<Empty> getRsData() {
        return RsData.of(resultCode, msg, new Empty());
    }
}
