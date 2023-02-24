package com.egeuniversity.Tez.Service.Order;

import com.egeuniversity.Tez.Model.Order.*;
import com.egeuniversity.Tez.Model.Product.Product;
import com.egeuniversity.Tez.Model.Utility.LocalDateTimeUtility;
import com.egeuniversity.Tez.Repository.Customer.CustomerRepository;
import com.egeuniversity.Tez.Repository.Order.OrderRepository;
import com.egeuniversity.Tez.Repository.Product.ProductRepository;
import com.egeuniversity.Tez.Service.Cart.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final  LocalDateTimeUtility localDateTimeUtility;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        Order order = assembleAddOrder(orderRequestDto);
        if(order != null){
            order.getLineItems().forEach(orderLine -> orderLine.setOrders(order));
            orderRepository.save(order);

            return OrderResponseDto.builder()
                    .orderStatus(OrderStatus.CREATED)
                    .situation("Sipariş başarıyla oluşturuldu")
                    .build();
        }
        else{
            return OrderResponseDto.builder()
                    .orderStatus(OrderStatus.PASSIVE)
                    .situation("Sipariş oluşturulurken bir hata ile karşılaşıldı.")
                    .build();
        }

    }

    private Order assembleAddOrder(OrderRequestDto orderRequestDto) {
        List<OrderLineItem> orderLineItemList = assembleOrderLineItem(orderRequestDto.getOrderLineItemRequest());

        if(orderLineItemList.size() != 0){
            return Order.builder()
                    .totalPrice(
                            orderLineItemList
                                    .stream()
                                    .map(order -> order.getLinePrice().multiply(BigDecimal.valueOf(order.getQuantity())))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add))
                    .orderDate(localDateTimeUtility.getCurrentDateTime())
                    .orderStatus(OrderStatus.CREATED)
                    .createdAt(localDateTimeUtility.getCurrentDateTime())
                    .updatedAt(localDateTimeUtility.getCurrentDateTime())
                    .lineItems(orderLineItemList)
                    .customer(customerRepository.getById(orderRequestDto.getCustomerId()))
                    .build();
        }
        else {
            LOGGER.info("All line items are invalid for the order.");
            return null;
        }

    }

    private List<OrderLineItem> assembleOrderLineItem(List<OrderLineItemRequestDto> orderLineItemRequest) {
        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        orderLineItemRequest.forEach(lineItem -> {
            Product product =  productRepository.getById(lineItem.getProductId());
            if(product.getStock() > lineItem.getQuantity()){
                OrderLineItem orderLineItem = OrderLineItem.builder()
                        .quantity(lineItem.getQuantity())
                        .linePrice(BigDecimal.valueOf(productRepository.getById(lineItem.getProductId()).getPrice()))
                        .product(product)
                        .createdAt(localDateTimeUtility.getCurrentDateTime())
                        .updatedAt(localDateTimeUtility.getCurrentDateTime())
                        .build();
                orderLineItemList.add(orderLineItem);

                product.setStock(product.getStock() - lineItem.getQuantity());
                productRepository.save(product);
            }
            else{
                LOGGER.info("Product stock is not enough for this line item " + product.getId());
            }
        });
        return orderLineItemList;
    }
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponseDto deleteOrder(Integer id) {
        Order order = orderRepository.getById(id);
        order.setOrderStatus(OrderStatus.DELETED);
        order.setUpdatedAt(localDateTimeUtility.getCurrentDateTime());

        return OrderResponseDto.builder()
                .orderStatus(OrderStatus.DELETED)
                .situation("Sipariş silindi")
                .build();
    }
}
