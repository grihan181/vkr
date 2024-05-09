package ru.avanesyan.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Product;
import ru.avanesyan.vkr.model.Provider;
import ru.avanesyan.vkr.repo.ProviderRepository;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public Provider getInfoAboutProduct(Product product) {
        Provider result = null;
        Set<Provider> providers = product.getProviders();

        for (Provider provider: providers) {
            if (sendApi(provider)) {
                result = provider;
                break;
            }
        }

        return result;
    }


    public boolean sendApi(Provider provider) {
        String api = provider.getApi();
        Random random = new Random();
        return random.nextBoolean();
    }
}
