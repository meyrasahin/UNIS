package com.egeuniversity.Tez.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
