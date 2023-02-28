package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
