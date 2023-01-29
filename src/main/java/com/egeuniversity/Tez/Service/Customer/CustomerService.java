package com.egeuniversity.Tez.Service.Customer;

import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;
import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.University.UniversityRequestDto;

public interface CustomerService {
    Customer findByName(String name);
    Customer addCustomer(CustomerRequestDto customerRequestDto);
}
