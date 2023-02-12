package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;
import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;
import com.egeuniversity.Tez.Service.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(path = "/create")
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        customerService.addCustomer(customerRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    @ResponseBody
    public Customer get(@RequestParam Integer id){
        Customer customer = customerService.getById(id);
        return customer;
    }

    @PostMapping(path = "/saveAddress")
    public Customer saveAddress(@RequestParam Integer customerId, @RequestBody AddressRequestDTO addressRequestDTO) {
        Customer customer = customerService.addCustomerAddress(customerId, addressRequestDTO);
        return customer;
    }

    @DeleteMapping(path = "/deleteAddress")
    public Customer deleteAddress(@RequestParam Integer customerId, @RequestParam Integer addressId) {
        Customer customer = customerService.removeCustomerAddress(addressId, customerId);
        return customer;
    }

    @GetMapping("/listAddresses")
    @ResponseBody
    public List<Address> listAddresses(@RequestParam Integer id){
        List<Address> addressList = customerService.listCustomerAddresses(id);
        if(addressList != null || addressList.size() != 0)
            return addressList;
        else
            return new ArrayList<>();
    }

    @PutMapping(path = "/updateAddress")
    public Address updateAddress(@RequestParam Integer addressId, @RequestBody AddressRequestDTO addressDTO){
        return customerService.updateCustomerAddress(addressId, addressDTO);
    }
}
