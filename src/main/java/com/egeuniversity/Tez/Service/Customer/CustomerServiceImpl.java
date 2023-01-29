package com.egeuniversity.Tez.Service.Customer;

import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerRequestDto;
import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Repository.Customer.CustomerRepository;
import com.egeuniversity.Tez.Repository.University.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UniversityRepository universityRepository;
    @Override
    public Customer findByName(String name) {
        return null;
    }

    @Override
    public Customer addCustomer(CustomerRequestDto customerRequestDto) {
        return customerRepository.save(assembleAddCustomer(customerRequestDto));
    }

    private Customer assembleAddCustomer(CustomerRequestDto customerRequestDto) {
        Optional<University> university = universityRepository.findById(customerRequestDto.getUniversityId());
        return Customer.builder()
                .username(customerRequestDto.getUsername())
                .password(customerRequestDto.getPassword())
                .name(customerRequestDto.getName())
                .surname(customerRequestDto.getSurname())
                .address(customerRequestDto.getAddress())
                .phone(customerRequestDto.getPhone())
                .university(university.get())
                .build();

    }

}
