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

   // @Scheduled(cron = "*/1 * * * * *")
    /*public void useProductBuyer() {
        int page = 0;
        Pageable pageable = PageRequest.of(page, countElem);

        Page<ProductBuyer> productBuyers = productBuyerRepository.findAll(pageable);

        for(int i = 0; i < productBuyers.getTotalPages(); i++) {
            pageable = PageRequest.of(i, countElem);
            productBuyers = productBuyerRepository.findAll(pageable);

            for(ProductBuyer product : productBuyers) {
                product.setQuantity(product.getQuantity() - 1);
                productBuyerRepository.save(product);
            }
        }
    }*/

    //@Scheduled(cron = "*/5 * * * * *")
/*    public void checkProduct() {
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
                int daysTime = (int) Math.ceil((product.getBufer() - product.getMinValue()) / product.getSpeed());
                int orderAmount = (int) Math.ceil(product.getMaxValue() - product.getQuantity() + daysTime * product.getSpeed());
                Orders orders = new Orders();
                orders.setProduct(product);

                providerService.getStartOrderInfo(orders, daysTime, orderAmount);


                orderService.createOrder(orders);
            }
        }
    }*/

    private void setSpeed(Product product) {
        int newQuantity = getProductBuyerQuantity(product);
        System.out.println(product);

        product.setSpeed(Math.abs(newQuantity * 1.0 - product.getQuantity()));
        product.setQuantity(newQuantity);
        System.out.println(product);

        productRepository.save(product);
    }
    private int getProductBuyerQuantity(Product product) {
        return productBuyerRepository.findById(product.getBuyerId()).getQuantity();
    }
}
