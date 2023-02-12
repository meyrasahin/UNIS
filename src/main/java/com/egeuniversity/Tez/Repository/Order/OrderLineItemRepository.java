package com.egeuniversity.Tez.Repository.Order;

import com.egeuniversity.Tez.Model.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemRepository extends JpaRepository<Order, Integer>{
}
