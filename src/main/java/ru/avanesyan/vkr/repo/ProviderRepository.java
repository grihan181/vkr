package ru.avanesyan.vkr.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.Provider;

@Repository
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {

}
