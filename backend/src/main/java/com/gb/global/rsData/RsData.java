package com.gb.global.rsData;

import com.gb.standard.base.Empty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RsData<T> {
    @NonNull
    private final String resultCode;

    @NonNull
    private final String msg;

    @NonNull
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
