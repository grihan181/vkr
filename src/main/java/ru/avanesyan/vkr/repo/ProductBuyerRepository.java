package ru.avanesyan.vkr.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.modelBuyer.ProductBuyer;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "productBuyer", path = "productBuyer")
public interface ProductBuyerRepository extends PagingAndSortingRepository<ProductBuyer, Long> {
    Optional<ProductBuyer> findById(Long id);
}
