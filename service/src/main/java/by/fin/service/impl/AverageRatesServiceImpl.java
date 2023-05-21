package by.fin.service.impl;

import by.fin.repository.CurrencyRepository;
import by.fin.repository.RatesRepository;
import by.fin.repository.WeekendsRepository;
import by.fin.repository.entity.Currency;
import by.fin.repository.entity.Rate;
import by.fin.repository.entity.Weekend;
import by.fin.service.AverageRatesService;
import by.fin.service.RatesService;
import by.fin.service.WeekendService;
import by.fin.service.dto.RateDto;
import by.fin.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AverageRatesServiceImpl implements AverageRatesService {
    private final RatesService ratesService;
    private final WeekendService weekendService;

    @Override
    public RateDto calculateAverageRatesByMonthAndYear(long currencyId, short month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<RateDto> rates = ratesService.findRatesByCurrencyIdBetween(currencyId, start, end);

        List<LocalDate> weekends = weekendService.findAllWeekendDatesBetween(start, end);

        List<RateDto> workdayRates = rates.stream()
                .filter(rate -> !weekends.contains(rate.getDate()))
                .toList();

        BigDecimal product = workdayRates.stream()
                .map(RateDto::getRate)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        BigDecimal exponent = BigDecimal.ONE.divide(BigDecimal.valueOf(workdayRates.size()), MathContext.DECIMAL128);

        return new RateDto(currencyId, start, BigDecimal.valueOf(Math.pow(product.doubleValue(), exponent.doubleValue())));
    }
}
