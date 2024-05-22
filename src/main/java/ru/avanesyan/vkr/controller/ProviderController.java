package ru.avanesyan.vkr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avanesyan.vkr.service.ProviderService;

@RestController
@CrossOrigin
@RequestMapping(value = "/provider")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
}
