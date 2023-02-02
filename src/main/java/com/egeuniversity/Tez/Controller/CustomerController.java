package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;
import com.egeuniversity.Tez.Service.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(path = "/addCustomers")
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        customerService.addCustomer(customerRequestDto);
        return ResponseEntity.ok().build();
    }

}
