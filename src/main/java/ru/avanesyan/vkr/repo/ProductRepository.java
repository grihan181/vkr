package ru.avanesyan.vkr.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.avanesyan.vkr.model.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product save(Product entity);

    Page<Product> findAll(Pageable pageable);
}
