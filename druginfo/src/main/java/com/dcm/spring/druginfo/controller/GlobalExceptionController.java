package com.dcm.spring.druginfo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionController {
    public String NotFoundException() {
        return "error"; // 이는 만든 에러 페이지의 이름과 일치해야 합니다.
    }
}
