package ru.avanesyan.vkr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.avanesyan.vkr.dto.DateDto;
import ru.avanesyan.vkr.model.Orders;
import ru.avanesyan.vkr.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("{page}/{size}")
    public Page<Orders> getOrder(@PathVariable int page, @PathVariable int size) {
        return orderService.getOrders(page, size);
    }

    @PostMapping("/getDashboard")
    public List<Orders> getDashboard(@RequestBody DateDto dateDto) {
        return orderService.getDashboard(dateDto.getProduct(), dateDto.getStartDate(), dateDto.getEndDate());
    }
    @PostMapping
    public Orders saveOrder(@RequestBody Orders orders) {
        return orderService.saveOrder(orders);
    }
}
