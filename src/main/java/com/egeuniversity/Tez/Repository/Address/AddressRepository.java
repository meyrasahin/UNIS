package com.egeuniversity.Tez.Repository.Address;

import com.egeuniversity.Tez.Model.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("select a from Address as a where a.id=?1")
    Address getById(Integer id);
}
