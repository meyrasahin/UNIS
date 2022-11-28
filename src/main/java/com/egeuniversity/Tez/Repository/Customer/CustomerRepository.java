package com.egeuniversity.Tez.Repository.Customer;

import com.egeuniversity.Tez.Model.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //@Query("select Customer From Customer where name=:nameParam ")
    public Customer findByName(String nameParam);

}
