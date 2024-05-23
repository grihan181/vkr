package ru.avanesyan.vkr.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avanesyan.vkr.model.Orders;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.enums.DeliveryStatus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByProduct(Product product);
    Page<Orders> findAll(Pageable pageable);
    long countByDeliveryStatus(DeliveryStatus deliveryStatus);

    List<Orders> findAllByProductAndArrivalDateBetweenOrderByArrivalDate(Product product, LocalDate start, LocalDate end);
}
