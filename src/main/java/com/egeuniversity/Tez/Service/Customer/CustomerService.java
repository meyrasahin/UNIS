package com.egeuniversity.Tez.Service.Customer;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;
import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(CustomerRequestDto customerRequestDto);
    Customer getById(Integer id);
    Customer addCustomerAddress(Integer customerId, AddressRequestDTO addressDTO);
    Customer removeCustomerAddress(Integer addressId, Integer customerId);
    List<Address> listCustomerAddresses(Integer customerId);
    Address updateCustomerAddress( Integer addressId, AddressRequestDTO addressDTO);
}
