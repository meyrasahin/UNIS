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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public Cart getCart(Integer id) {
        return cartRepository.get(id);
    }

    @Override
    public Cart createCart(CartRequestDTO cartRequestDTO) {
        Cart cart = assembleCreateCart(cartRequestDTO);
        if(cart != null){
            cart.getLineItems().forEach((item) -> item.setCart(cart));
            return cartRepository.save(cart);
        }
        return new Cart();
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
        if(lineItem.getProduct().getStock() > lineItem.getQuantity() + 1){
            lineItem.setQuantity(lineItem.getQuantity() + 1);
            lineItem.setLinePrice(lineItem.getQuantity() * lineItem.getProduct().getPrice());
            lineItem.setUpdatedAt(localDateTimeUtility.getCurrentDateTime());
            cartLineItemRepository.save(lineItem);

            return updateCartQuantity(lineItem);
        }
        else{
            LOGGER.info("There is not enough stock for product id: " + lineItem.getProduct().getId());
            return lineItem.getCart();
        }

    }

    @Override
    public Cart addCartLineItem(CartLineItemRequestDTO lineItemRequestDTO, Integer cartId) {
        List<CartLineItemRequestDTO> source = new ArrayList<>();
        source.add(lineItemRequestDTO);
        List<CartLineItem> target = this.assembleCreateCartLineItem(source);

        CartLineItem targetItem = target.get(0);
        Cart cart = this.getCart(cartId);
        targetItem.setCart(cart);
        cartLineItemRepository.save(targetItem);

        cart.getLineItems().add(targetItem);
        cartRepository.save(cart);

        return updateCartQuantity(target.get(0));
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

        if(lineItemList.size() != 0){
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
        else{
            LOGGER.info("All line items are invalid for the cart.");
            return null;
        }
    }

    private List<CartLineItem> assembleCreateCartLineItem(List<CartLineItemRequestDTO> lineItemRequest){
        List<CartLineItem> targetList = new ArrayList<>();

        for (CartLineItemRequestDTO item : lineItemRequest) {
            Product product = productService.getProduct(item.getProductId());
            if(product.getStock() > item.getQuantity()){
                CartLineItem targetItem = CartLineItem.builder()
                        .quantity(item.getQuantity())
                        .product(product)
                        .createdAt(localDateTimeUtility.getCurrentDateTime())
                        .updatedAt(localDateTimeUtility.getCurrentDateTime())
                        .linePrice(product.getPrice()*item.getQuantity())
                        .build();

                targetList.add(targetItem);
            }
            else{
                LOGGER.info("Product stock is not enough for this line item id:" + product.getId());
            }
        }

        return targetList;
    }


}
