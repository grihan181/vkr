package ru.avanesyan.vkr.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.Provider;

@RepositoryRestResource(collectionResourceRel = "provider", path = "provider")
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {

}
