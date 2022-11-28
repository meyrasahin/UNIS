package com.egeuniversity.Tez.Service.Customer;

import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Repository.Customer.CustomerRepository;
import com.egeuniversity.Tez.Service.Generic.BaseEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends BaseEntityServiceImpl<Integer, Customer> implements CustomerService {

    private CustomerRepository customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepo) {
        super(customerRepo);
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer findByName(String name) {
        return customerRepo.getOne(1);
    }
}
