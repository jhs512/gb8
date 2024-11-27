package com.gb.global.rsData;

import com.gb.standard.base.Empty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RsData<T> {
    @NonNull
    private final int statusCode;

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
        int statusCode = Integer.parseInt(resultCode.split("-")[1]);

        return new RsData<>(statusCode, resultCode, msg, data);
    }

    public static <T> RsData<T> of(
            String resultCode,
            String msg
    ) {
        return of(resultCode, msg, (T) new Empty());
    }
}
