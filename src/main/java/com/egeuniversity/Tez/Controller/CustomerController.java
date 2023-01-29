package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;
import com.egeuniversity.Tez.Service.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(path = "/customer/addCustomers")
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        customerService.addCustomer(customerRequestDto);
        return ResponseEntity.ok().build();
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
