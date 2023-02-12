package com.egeuniversity.Tez.Repository.Customer;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer as c where c.id=?1")
    Customer get(Integer id);

    @Query("select c from Customer as c where c.id=?1")
    Customer getById(Integer id);

    @Query("select ca from Customer as c join c.addresses as ca where c.id=?1")
    List<Address> listAddresses(Integer id);
}
