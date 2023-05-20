package by.fin.web.controller;

import by.fin.service.RatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/rates")
@RestController
@RequiredArgsConstructor
public class RatesController {
    private final RatesService ratesService;
}
