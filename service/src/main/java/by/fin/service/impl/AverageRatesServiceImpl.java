package by.fin.service.impl;

import by.fin.service.AverageRatesService;
import by.fin.service.RatesService;
import by.fin.service.WeekendService;
import by.fin.service.dto.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AverageRatesServiceImpl implements AverageRatesService {
    private final RatesService ratesService;
    private final WeekendService weekendService;

    @Override
    public Optional<RateDto> calculateAverageRatesByMonthAndYear(long currencyId, short month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<RateDto> rates = ratesService.findRatesByCurrencyIdBetween(currencyId, start, end);

        List<LocalDate> weekends = weekendService.findAllWeekendDatesBetween(start, end);

        List<BigDecimal> workdaysRates = getWorkdaysRates(rates, weekends);
        if (workdaysRates.size() == 0)
            return Optional.empty();

        return Optional.of(new RateDto(currencyId, start, calculateGeometricalAverage(workdaysRates)));
    }

    private List<BigDecimal> getWorkdaysRates(List<RateDto> rates, List<LocalDate> weekends) {
        return rates.stream()
                .filter(rate -> !weekends.contains(rate.getDate()))
                .map(RateDto::getRate)
                .toList();
    }

    private static BigDecimal calculateGeometricalAverage(List<BigDecimal> values) {
        if (values.size() == 0)
            return BigDecimal.ZERO;

        BigDecimal product = BigDecimal.ONE;

        for (BigDecimal value : values) {
            product = product.multiply(value);
        }

        BigDecimal exponent = BigDecimal.ONE.divide(BigDecimal.valueOf(values.size()), MathContext.DECIMAL128);

        return BigDecimal.valueOf(Math.pow(product.doubleValue(), exponent.doubleValue()));
    }
}
