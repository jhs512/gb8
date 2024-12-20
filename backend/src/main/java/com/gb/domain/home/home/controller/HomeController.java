package com.gb.domain.home.home.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Tag(name = "HomeController", description = "Home 컨트롤러")
@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
public class HomeController {
    @GetMapping
    @ResponseBody
    @Operation(summary = "메인")
    public String showMain() {
        return "안녕하세요.";
    }
}