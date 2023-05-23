package by.fin.web.controller;

import by.fin.service.CurrencyService;
import by.fin.service.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/currencies")
@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getCurrencies(
            @RequestParam(value = "code", required = false) String abbreviation
    ) {
        List<CurrencyDto> currencies;

        if (abbreviation == null) {
            currencies = currencyService.findAll();
        } else {
            currencies = currencyService.findByAbbreviation(abbreviation);
        }
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDto> getCurrencies(@PathVariable("id") Long id) {
        Optional<CurrencyDto> currency = currencyService.findCurrencyById(id);
        if (currency.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(currency.get(), HttpStatus.OK);
    }
}
