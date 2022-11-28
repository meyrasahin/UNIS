package com.egeuniversity.Tez.Service.Customer;

import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Service.Generic.BaseEntityService;

public interface CustomerService extends BaseEntityService<Integer, Customer> {
    Customer findByName(String name);
}
