package com.egeuniversity.Tez.Service.Customer;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;
import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;
import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Repository.Customer.CustomerRepository;
import com.egeuniversity.Tez.Repository.University.UniversityRepository;
import com.egeuniversity.Tez.Service.Address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UniversityRepository universityRepository;

    private final AddressService addressService;

    @Override
    public Customer addCustomer(CustomerRequestDto customerRequestDto) {
        return customerRepository.save(assembleAddCustomer(customerRequestDto));
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository.getById(id);
    }

    @Override
    public Customer addCustomerAddress(Integer customerId, AddressRequestDTO addressDTO) {
        Address address = addressService.addAddress(addressDTO);
        Customer customer = this.getById(customerId);
        customer.getAddresses().add(address);
        return customerRepository.save(customer);
    }

    @Override
    public Customer removeCustomerAddress(Integer addressId, Integer customerId) {
        Customer customer = this.getById(customerId);
        Address address = addressService.getAddress(addressId);
        customer.getAddresses().remove(address);
        return customerRepository.save(customer);
    }

    @Override
    public List<Address> listCustomerAddresses(Integer customerId) {
        return customerRepository.listAddresses(customerId);
    }

    @Override
    public Address updateCustomerAddress(Integer addressId, AddressRequestDTO addressDTO) {
        return addressService.updateAddress(addressId, addressDTO);
    }

    private Customer assembleAddCustomer(CustomerRequestDto customerRequestDto) {
        University university = universityRepository.get(customerRequestDto.getUniversityId());
        return Customer.builder()
                .username(customerRequestDto.getUsername())
                .password(customerRequestDto.getPassword())
                .name(customerRequestDto.getName())
                .surname(customerRequestDto.getSurname())
                .addresses(new ArrayList<>())
                .phone(customerRequestDto.getPhone())
                .university(university)
                .customerType(customerRequestDto.getCustomerType())
                .build();
    }
}
