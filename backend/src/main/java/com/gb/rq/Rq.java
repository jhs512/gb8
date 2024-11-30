package com.gb.rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public void setStatusCode(int statusCode) {
        resp.setStatus(statusCode);
    }

    public String getHeader(String headerName, String defaultValue) {
        String value = req.getHeader(headerName);

        if (value == null) {
            return defaultValue;
        }

        return value;
    }
}
