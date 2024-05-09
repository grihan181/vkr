package ru.avanesyan.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Order;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.Provider;
import ru.avanesyan.vkr.repo.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void createOrder(Product product, Provider provider) {
        //TODO Create Order
        System.out.println("Order Created for - " + product.getId() + " Speed Created for - " + product.getSpeed() + " Provider - " + provider.getId());
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findByProduct(Product product) {
        return orderRepository.findByProduct(product);
    }

    public Page<Order> getOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("arrivalDate")));
    }
}
