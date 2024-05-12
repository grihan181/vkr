package ru.avanesyan.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Orders;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.repo.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void createOrder(Orders orders) {
        orderRepository.save(orders);
    }

    public Orders saveOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    public List<Orders> findByProduct(Product product) {
        return orderRepository.findByProduct(product);
    }

    public Page<Orders> getOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("arrivalDate")));
    }

    public List<Orders> getDashboard(Product product, LocalDate start, LocalDate end) {
        return orderRepository.findAllByProductAndArrivalDateBetween(product, start, end);
    }
}
