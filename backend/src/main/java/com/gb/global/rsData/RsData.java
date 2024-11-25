package com.gb.global.rsData;

import com.gb.standard.base.Empty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private final T data;

    public static <T> RsData<T> of(
            String resultCode,
            String msg,
            T data
    ) {
        return new RsData<>(resultCode, msg, data);
    }

    public static <T> RsData<T> of(
            String resultCode,
            String msg
    ) {
        return new RsData<>(resultCode, msg, (T) new Empty());
    }
}
