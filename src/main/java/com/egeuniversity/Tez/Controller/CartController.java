package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Cart.Cart.Cart;
import com.egeuniversity.Tez.Model.Cart.Cart.CartRequestDTO;
import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItemRequestDTO;
import com.egeuniversity.Tez.Service.Cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/get")
    @ResponseBody
    public Cart getById(@RequestParam Integer id){
        Cart cart = cartService.getCart(id);
        return cart;
    }

    @PostMapping(path = "/create")
    public Cart createShoppingCart(@RequestBody CartRequestDTO cartRequestDTO) {
        return cartService.createCart(cartRequestDTO);
    }

    @DeleteMapping(path= "/deleteLineItem")
    public Cart deleteCartLineItem(@RequestBody Integer id) {
        Cart cart = cartService.deleteCartLineItem(id);
        return cart;
    }

    @PutMapping(path = "/updateQuantity")
    public Cart updateQuantity(@RequestParam Integer cartLineItemId, @RequestParam String operation){
        Cart cart = null;
        switch (operation){
            case "decrease":
                cart = cartService.decreaseQuantity(cartLineItemId);
                break;
            case "increase":
                cart = cartService.increaseQuantity(cartLineItemId);
                break;
        }

        return cart;
    }

    @PostMapping(path = "/addItem")
    public Cart addCartLineItem(@RequestParam Integer cartId, @RequestBody CartLineItemRequestDTO requestDTO) {
        return cartService.addCartLineItem(requestDTO, cartId);
    }

}
