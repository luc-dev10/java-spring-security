package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("unauthorized-access")
    public String getUnauthorizedAccess() {
        return "unauthorized-access";
    }

}
