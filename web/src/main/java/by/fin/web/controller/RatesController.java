package by.fin.web.controller;

import by.fin.service.RatesService;
import by.fin.service.dto.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/rates")
@RestController
@RequiredArgsConstructor
public class RatesController {
    private final RatesService ratesService;

    @GetMapping("/{id}")
    public ResponseEntity<List<RateDto>> getRates(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ratesService.findRatesByCurrencyId(id), HttpStatus.OK);
    }
}
