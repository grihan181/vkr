package ru.avanesyan.vkr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.avanesyan.vkr.service.ProviderService;

@RestController
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
}
