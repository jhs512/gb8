package com.gb.domain.global.exceptionsHandlers;

import com.gb.domain.global.exceptions.GlobalException;
import com.gb.global.rsData.RsData;
import com.gb.standard.base.Empty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<RsData<Empty>> handle(GlobalException ex) {
        return new ResponseEntity<>(ex.getRsData(), HttpStatus.BAD_REQUEST);
    }
}
