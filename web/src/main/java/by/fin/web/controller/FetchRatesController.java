package by.fin.web.controller;

import by.fin.service.RatesService;
import by.fin.service.RemoteRatesService;
import by.fin.service.dto.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("api/fetch-rates")
@RestController
@RequiredArgsConstructor
public class FetchRatesController {
    private final RatesService ratesService;
    private final RemoteRatesService remoteRatesService;

    @GetMapping("/{id}")
    public ResponseEntity<List<RateDto>> fetchRatesByDate(
            @PathVariable("id") Long id,
            @RequestParam("startdate") LocalDate startDate,
            @RequestParam("enddate") LocalDate endDate
    ) {
        List<RateDto> fetchedRates = remoteRatesService.fetchRatesByCurrencyIdAndDates(id, startDate, endDate);

        return new ResponseEntity<>(fetchedRates, HttpStatus.OK);
    }
}
