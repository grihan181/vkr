package ru.avanesyan.vkr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avanesyan.vkr.model.Factory;
import ru.avanesyan.vkr.model.Provider;
import ru.avanesyan.vkr.repo.FactoryRepository;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final FactoryRepository factoryRepository;


    public int getTravelTime(Provider provider, Factory factory) {

        //TODO интеграция с картами
        return 0;
    }
}
