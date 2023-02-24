package com.egeuniversity.Tez.Service.Cart;

import com.egeuniversity.Tez.Model.Cart.Cart.Cart;
import com.egeuniversity.Tez.Model.Cart.Cart.CartRequestDTO;
import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItemRequestDTO;

public interface CartService {
    Cart getCart(Integer id);
    Cart createCart(CartRequestDTO cartRequestDTO);
    void deleteCart(Cart cart);
    Cart deleteCartLineItem(Integer id);
    Cart decreaseQuantity(Integer cartLineItemId);
    Cart increaseQuantity(Integer cartLineItemId);
    Cart addCartLineItem(CartLineItemRequestDTO lineItemRequestDTO, Integer cartId);
}
