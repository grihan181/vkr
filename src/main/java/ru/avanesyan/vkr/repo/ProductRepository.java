package ru.avanesyan.vkr.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.avanesyan.vkr.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product save(Product entity);

    Page<Product> findAllByOrderByIdAsc(Pageable pageable);
    List<Product> findByNameContainingIgnoreCase(String name);

    Product findByName(String name);
}
