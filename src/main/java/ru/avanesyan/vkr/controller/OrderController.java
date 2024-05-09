package ru.avanesyan.vkr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.avanesyan.vkr.model.Order;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.service.OrderService;
import ru.avanesyan.vkr.service.ProductService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{page}/{size}")
    public Page<Order> getOrder(@PathVariable int page, @PathVariable int size) {
        return orderService.getOrders(page, size);
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
