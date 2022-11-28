package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @GetMapping("/getTest")
    public String getTest(){
        return "test1";
    }

    @GetMapping("/get")
    public String getByName(){
        return customerService.findByName("meyra").getName();
    }
}
