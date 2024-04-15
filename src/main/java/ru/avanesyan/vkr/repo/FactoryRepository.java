package ru.avanesyan.vkr.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avanesyan.vkr.model.Product;

@RepositoryRestResource(collectionResourceRel = "factory", path = "factory")
public interface FactoryRepository extends PagingAndSortingRepository<Product, Long> {
}
