package com.egeuniversity.Tez.Repository.Cart;

import com.egeuniversity.Tez.Model.Cart.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart as c where c.id=?1")
    Cart get(Integer id);
}
