package com.egeuniversity.Tez.Service.Order;

import com.egeuniversity.Tez.Model.Order.*;
import com.egeuniversity.Tez.Model.Utility.LocalDateTimeUtility;
import com.egeuniversity.Tez.Repository.Customer.CustomerRepository;
import com.egeuniversity.Tez.Repository.Order.OrderRepository;
import com.egeuniversity.Tez.Repository.Product.ProductRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        Order order = assembleAddOrder(orderRequestDto);
        order.getLineItems().forEach(orderLine -> orderLine.setOrders(order));
        orderRepository.save(order);

        return OrderResponseDto.builder()
                .orderStatus(OrderStatus.CREATED)
                .situation("Sipariş başarıyla oluşturuldu")
                .build();
    }

    private Order assembleAddOrder(OrderRequestDto orderRequestDto) {
        List<OrderLineItem> orderLineItemList = assembleOrderLineItem(orderRequestDto.getOrderLineItemRequest());
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

    private List<OrderLineItem> assembleOrderLineItem(List<OrderLineItemRequestDto> orderLineItemRequest) {
        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        orderLineItemRequest.forEach(lineItem -> {
            OrderLineItem orderLineItem = OrderLineItem.builder()
                    .quantity(lineItem.getQuantity())
                    .linePrice(BigDecimal.valueOf(productRepository.getById(lineItem.getProductId()).getPrice()))
                    .product(productRepository.getById(lineItem.getProductId()))
                    .createdAt(localDateTimeUtility.getCurrentDateTime())
                    .updatedAt(localDateTimeUtility.getCurrentDateTime())
                    .build();
            orderLineItemList.add(orderLineItem);
        });
        return orderLineItemList;
    }
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponseDto deleteOrder(Integer id) {
        orderRepository.delete(orderRepository.getById(id));
        return OrderResponseDto.builder()
                .orderStatus(OrderStatus.DELETED)
                .situation("Sipariş silindi")
                .build();
    }
}
