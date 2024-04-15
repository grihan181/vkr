package ru.avanesyan.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.Provider;

@Service
@RequiredArgsConstructor
public class OrderService {
    public void createOrder(Product product, Provider provider) {
        //TODO Create Order
        System.out.println("Order Created for - " + product.getId() + " Speed Created for - " + product.getSpeed() + " Provider - " + provider.getId());
    }
}
