package ru.avanesyan.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Orders;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.enums.DeliveryStatus;
import ru.avanesyan.vkr.repo.OrderRepository;
import ru.avanesyan.vkr.repo.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
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

    public List<Orders> getDashboard(Long productId, LocalDate start, LocalDate end) {
        Product product = productRepository.findById(productId);
        return orderRepository.findAllByProductAndArrivalDateBetween(product, start, end);
    }

    public List<Orders> getOrdersByName(String name) {
        Product product = productService.getProductByName(name);
        return findByProduct(product);
    }

    public long getCountByDeliveryStatus(String status) {
        return orderRepository.countByDeliveryStatus(DeliveryStatus.valueOf(status));
    }
}
