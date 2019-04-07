package com.xx.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InitController {
    @ResponseBody
    @RequestMapping("/info")
    public String init() {
        return "hello init...";
    }
}
