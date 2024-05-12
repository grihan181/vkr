package ru.avanesyan.vkr.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Orders;
import ru.avanesyan.vkr.model.Provider;
import ru.avanesyan.vkr.model.enums.DeliveryStatus;
import ru.avanesyan.vkr.repo.ProviderRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public Orders getStartOrderInfo(Orders orders, int daysTime, int orderAmount) {
        Provider result = null;

        Set<Provider> providers = orders.getProduct().getProviders();

        for (Provider provider: providers) {
            Api apiInfo = sendApiTrueFalse(provider, daysTime, orderAmount);
            if (0 == apiInfo.getDays()) {
                result = provider;

                orders.setLostInDays(0);
                orders.setArrivalDate(apiInfo.getDate());
                orders.setDeliveryStatus(DeliveryStatus.ON_TIME);
                orders.setAmount(orderAmount);
                break;
            }
        }

        if(result == null) {
            LocalDate lowerTime = null;
            int days = 0;

            for (Provider provider : providers) {
                Api apiInfo = sendApiDates(provider, daysTime, orderAmount);

                if(lowerTime == null || lowerTime.isAfter(apiInfo.getDate())) {
                    result = provider;
                    lowerTime = apiInfo.getDate();
                    days = apiInfo.getDays();
                }
            }

            orders.setLostInDays(days);
            orders.setArrivalDate(lowerTime);
            orders.setDeliveryStatus(DeliveryStatus.LATE);
            orders.setAmount(orders.getProduct().getMaxValue());
        }

        orders.setProvider(result);
        return orders;
    }


    public Api sendApiTrueFalse(Provider provider, int daysTime, int orderAmount) {
        String api = provider.getApi();

        Random random = new Random();
        Api apiInfo = new Api();
        if(random.nextBoolean()) {
            apiInfo.setDate(LocalDate.now().plusDays(random.nextInt(daysTime)));
            apiInfo.setDays(0);
        } else {
            apiInfo.setDays(1);
        }

        return apiInfo;
    }

    public Api sendApiDates(Provider provider, int daysTime, int orderAmount) {
        String api = provider.getApi();

        Random random = new Random();
        int days = 1 + random.nextInt(5);

        return new Api(LocalDate.now().plusDays(daysTime + days), days);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Api {
        private LocalDate date;
        private int days;
    }
}
