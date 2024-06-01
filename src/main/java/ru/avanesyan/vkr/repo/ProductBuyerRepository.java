package ru.avanesyan.vkr.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.avanesyan.vkr.modelBuyer.ProductBuyer;

@RepositoryRestResource(collectionResourceRel = "productBuyer", path = "productBuyer")
@CrossOrigin
public interface ProductBuyerRepository extends PagingAndSortingRepository<ProductBuyer, Long> {
    ProductBuyer save(ProductBuyer entity);

    ProductBuyer findById(Long id);

    Page<ProductBuyer> findAllByOrderByIdAsc(Pageable pageable);
}
