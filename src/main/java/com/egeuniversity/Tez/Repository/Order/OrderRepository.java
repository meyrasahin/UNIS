package com.egeuniversity.Tez.Repository.Order;

import com.egeuniversity.Tez.Model.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order as o where o.id=?1")
    Order getById(Integer id);
}
