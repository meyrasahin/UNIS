package com.egeuniversity.Tez.Repository.Cart;

import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartLineItemRepository extends JpaRepository<CartLineItem, Integer> {
    @Query("select cl from CartLineItem as cl where cl.id=?1")
    CartLineItem get(Integer id);

}
