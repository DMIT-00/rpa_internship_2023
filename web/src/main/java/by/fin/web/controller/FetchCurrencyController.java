package by.fin.web.controller;

import by.fin.service.RemoteRatesService;
import by.fin.service.dto.CurrencyDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/fetch-currency")
@RestController
@RequiredArgsConstructor
public class FetchCurrencyController {
    private final RemoteRatesService remoteRatesService;

    @GetMapping
    public ResponseEntity<CurrencyDto> fetchCurrencyByAbbreviation(
            @RequestParam("currency") String currencyAbbreviation
    ) {
        CurrencyDto currency = remoteRatesService.fetchCurrencyByAbbreviation(currencyAbbreviation)
                .orElseThrow(() -> new EntityNotFoundException("Currency not found " + currencyAbbreviation));

        return new ResponseEntity<>(currency, HttpStatus.OK);
    }
}
