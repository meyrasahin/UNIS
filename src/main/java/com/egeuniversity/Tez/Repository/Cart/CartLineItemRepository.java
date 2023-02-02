package com.egeuniversity.Tez.Repository.Cart;

import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartLineItemRepository extends JpaRepository<CartLineItem, Integer> {
}
