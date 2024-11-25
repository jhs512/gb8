package com.gb.domain.global.exceptionsHandlers;

import com.gb.domain.global.exceptions.GlobalException;
import com.gb.global.rsData.RsData;
import com.gb.standard.base.Empty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<RsData<Empty>> handle(GlobalException ex) {
        return new ResponseEntity<>(ex.getRsData(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<RsData<Empty>> handleException(MethodArgumentNotValidException ex) {
        String resultCode = "400-" + ex.getBindingResult().getFieldError().getCode();
        String msg = ex.getBindingResult().getFieldError().getField() + " : " + ex.getBindingResult().getFieldError().getDefaultMessage();

        return new ResponseEntity<>(RsData.of(resultCode, msg), HttpStatus.BAD_REQUEST);
    }
}
