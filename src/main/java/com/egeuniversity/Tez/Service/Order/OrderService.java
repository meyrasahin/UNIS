package com.egeuniversity.Tez.Service.Order;

import com.egeuniversity.Tez.Model.Order.Order;
import com.egeuniversity.Tez.Model.Order.OrderRequestDto;
import com.egeuniversity.Tez.Model.Order.OrderResponseDto;
import java.util.List;

public interface OrderService {
    OrderResponseDto addOrder(OrderRequestDto orderRequestDto);
    List<Order> getAllOrders();
    OrderResponseDto deleteOrder(Integer id);
}
