package by.fin.web.controller;

import by.fin.service.RemoteCurrencyService;
import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/fetch-currency")
@RestController
@RequiredArgsConstructor
public class RemoteCurrencyController {
    private final RemoteCurrencyService remoteCurrencyService;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> fetchCurrency(
            @RequestParam(value = "code", required = false) String abbreviation
    ) {
        List<CurrencyDto> currencies;

        if (abbreviation == null) {
            currencies = remoteCurrencyService.findAll();
        } else {
            currencies = remoteCurrencyService.findByAbbreviation(abbreviation);
        }

        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }
}
