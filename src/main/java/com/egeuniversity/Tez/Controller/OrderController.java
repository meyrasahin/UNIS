package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Order.Order;
import com.egeuniversity.Tez.Model.Order.OrderRequestDto;
import com.egeuniversity.Tez.Model.Order.OrderResponseDto;
import com.egeuniversity.Tez.Service.Order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<OrderResponseDto> addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.addOrder(orderRequestDto));
    }

    @GetMapping(path = "/getAll")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping(path= "/delete")
    public ResponseEntity<OrderResponseDto> deleteOrder(@RequestParam Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
