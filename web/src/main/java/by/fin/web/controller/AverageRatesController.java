package by.fin.web.controller;

import by.fin.service.AverageRatesService;
import by.fin.service.dto.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/average-rates")
@RestController
@RequiredArgsConstructor
public class AverageRatesController {
    private final AverageRatesService averageRatesService;

    @GetMapping("/{id}")
    public ResponseEntity<RateDto> getAverageRate(
            @PathVariable("id") Long currencyId,
            @RequestParam("month") short month,
            @RequestParam("year") int year
    ) {
        Optional<RateDto> averageRate = averageRatesService.calculateAverageRatesByMonthAndYear(currencyId, month, year);
        if (averageRate.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(averageRate.get(), HttpStatus.OK);
    }
}
