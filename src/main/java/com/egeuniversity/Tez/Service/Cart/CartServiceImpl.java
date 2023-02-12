package com.egeuniversity.Tez.Service.Cart;

import com.egeuniversity.Tez.Model.Cart.Cart.Cart;
import com.egeuniversity.Tez.Model.Cart.Cart.CartRequestDTO;
import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItem;
import com.egeuniversity.Tez.Model.Cart.CartLineItem.CartLineItemRequestDTO;
import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.Utility.LocalDateTimeUtility;
import com.egeuniversity.Tez.Repository.Cart.CartLineItemRepository;
import com.egeuniversity.Tez.Repository.Cart.CartRepository;
import com.egeuniversity.Tez.Service.Customer.CustomerService;
import com.egeuniversity.Tez.Service.Product.ProductService;
import com.egeuniversity.Tez.Service.University.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartLineItemRepository cartLineItemRepository;

    private final UniversityService universityService;
    private final CustomerService customerService;
    private final ProductService productService;

    private final LocalDateTimeUtility localDateTimeUtility;

    @Override
    public Cart getCart(Integer id) {
        return cartRepository.get(id);
    }

    @Override
    public Cart createCart(CartRequestDTO cartRequestDTO) {
        Cart cart = assembleCreateCart(cartRequestDTO);
        cart.getLineItems().forEach((item) -> item.setCart(cart));
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public Cart deleteCartLineItem(Integer id) {
        CartLineItem lineItem = cartLineItemRepository.get(id);
        Cart cart = lineItem.getCart();
        cart.getLineItems().remove(lineItem);
        cartRepository.save(cart);

        cart = updateCartQuantity(lineItem);
        cartLineItemRepository.deleteById(id);

        return cart;
    }

    @Override
    public Cart decreaseQuantity(Integer cartLineItemId) {
        CartLineItem lineItem = cartLineItemRepository.get(cartLineItemId);
        lineItem.setQuantity(lineItem.getQuantity() - 1);
        lineItem.setLinePrice(lineItem.getQuantity() * lineItem.getProduct().getPrice());
        lineItem.setUpdatedAt(localDateTimeUtility.getCurrentDateTime());
        cartLineItemRepository.save(lineItem);

        return updateCartQuantity(lineItem);
    }

    @Override
    public Cart increaseQuantity(Integer cartLineItemId) {
        CartLineItem lineItem = cartLineItemRepository.get(cartLineItemId);
        lineItem.setQuantity(lineItem.getQuantity() + 1);
        lineItem.setLinePrice(lineItem.getQuantity() * lineItem.getProduct().getPrice());
        lineItem.setUpdatedAt(localDateTimeUtility.getCurrentDateTime());
        cartLineItemRepository.save(lineItem);

        return updateCartQuantity(lineItem);
    }

    private Cart updateCartQuantity(CartLineItem lineItem){
        Cart cart = lineItem.getCart();
        double totalPrice = cart.getLineItems().stream().mapToDouble(CartLineItem::getLinePrice).sum();
        lineItem.setUpdatedAt(localDateTimeUtility.getCurrentDateTime());
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);

        return cart;
    }

    private Cart assembleCreateCart(CartRequestDTO cartRequestDTO){
        University university = universityService.get(cartRequestDTO.getUniversityId());
        Customer customer = customerService.getById(cartRequestDTO.getCustomerId());

        List<CartLineItem> lineItemList = assembleCreateCartLineItem(cartRequestDTO.getLineItemsDTO());

        double totalPrice = lineItemList.stream().mapToDouble(CartLineItem::getLinePrice).sum();

        return Cart.builder()
                .createdAt(localDateTimeUtility.getCurrentDateTime())
                .updatedAt(localDateTimeUtility.getCurrentDateTime())
                .lineItems(lineItemList)
                .university(university)
                .customer(customer)
                .totalPrice(totalPrice)
                .build();
    }

    private List<CartLineItem> assembleCreateCartLineItem(List<CartLineItemRequestDTO> lineItemRequest){
        List<CartLineItem> targetList = new ArrayList<>();

        for (CartLineItemRequestDTO item : lineItemRequest) {
            Product product = productService.getProduct(item.getProductId());
            CartLineItem targetItem = CartLineItem.builder()
                    .quantity(item.getQuantity())
                    .product(product)
                    .createdAt(localDateTimeUtility.getCurrentDateTime())
                    .updatedAt(localDateTimeUtility.getCurrentDateTime())
                    .linePrice(product.getPrice()*item.getQuantity())
                    .build();

            targetList.add(targetItem);
        }

        return targetList;
    }


}
