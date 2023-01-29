package com.egeuniversity.Tez.Service.Customer;

import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;

public interface CustomerService {
    Customer findByName(String name);
    Customer addCustomer(CustomerRequestDto customerRequestDto);
}
