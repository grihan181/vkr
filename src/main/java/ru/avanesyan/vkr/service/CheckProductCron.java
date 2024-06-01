package ru.avanesyan.vkr.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Orders;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.modelBuyer.ProductBuyer;
import ru.avanesyan.vkr.repo.ProductBuyerRepository;
import ru.avanesyan.vkr.repo.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckProductCron {
    private final OrderService orderService;
    private final ProviderService providerService;
    private final ProductRepository productRepository;
    private final ProductBuyerRepository productBuyerRepository;
    private final int countElem = 2;

    //@Scheduled(cron = "*/5 * * * * *")
    public void useProductBuyer() {
        int page = 0;
        Pageable pageable = PageRequest.of(page, countElem);

        Page<ProductBuyer> productBuyers = productBuyerRepository.findAllByOrderByIdAsc(pageable);

        int count = 5;
        for(int i = 0; i < productBuyers.getTotalPages(); i++) {
            pageable = PageRequest.of(i, countElem);
            productBuyers = productBuyerRepository.findAllByOrderByIdAsc(pageable);

            for(ProductBuyer product : productBuyers) {
                if(product.getId() == 1) {
                    product.setQuantity(product.getQuantity() - 10);
                } else {
                    product.setQuantity(product.getQuantity() - count++);
                }
                productBuyerRepository.save(product);
            }
        }
    }

    //@Scheduled(cron = "*/20 * * * * *")
    public void checkProduct() {
        useProductBuyer();

        int page = 0;
        Pageable pageable = PageRequest.of(page, countElem);


        Page<Product> products = productRepository.findAll(pageable);

        for(int i = 0; i < products.getTotalPages(); i++) {
            pageable = PageRequest.of(i, countElem);
            products = productRepository.findAllByOrderByIdAsc(pageable);

            products.getContent().forEach(this::setSpeed);
            List<Product> filteredProducts = products.getContent().stream().filter(product -> product.getQuantity() <= product.getBufer()).toList();
            if(filteredProducts.isEmpty()) {
                continue;
            }

            for(Product product : filteredProducts) {
                int daysTime = (int) Math.ceil((product.getQuantity() - product.getMinValue()) / product.getSpeed());
                int orderAmount = (int) Math.ceil(product.getMaxValue() - product.getQuantity() + daysTime * product.getSpeed());
                Orders orders = new Orders();
                orders.setProduct(product);

                if(orderAmount > product.getMaxValue()) {
                    orderAmount = product.getMaxValue();
                }

                orders.setBufer(product.getBufer());
                orders.setMaxValue(product.getMaxValue());
                orders.setMinValue(product.getMinValue());

                providerService.getStartOrderInfo(orders, daysTime, orderAmount);


                System.out.println(orders);;
                orderService.createOrder(orders);

                ProductBuyer productBuyer =  productBuyerRepository.findById(product.getBuyerId());
                productBuyer.setQuantity(orders.getAmount());
                productBuyerRepository.save(productBuyer);
            }
        }
    }

    private void setSpeed(Product product) {
        int newQuantity = getProductBuyerQuantity(product);

        product.setSpeed(Math.abs(newQuantity * 1.0 - product.getQuantity()));
        product.setQuantity(newQuantity);
        System.out.println(product);

        productRepository.save(product);
    }
    private int getProductBuyerQuantity(Product product) {
        return productBuyerRepository.findById(product.getBuyerId()).getQuantity();
    }
}
