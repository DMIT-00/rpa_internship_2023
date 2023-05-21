package by.fin.service;

import by.fin.service.dto.RateDto;

import java.util.Optional;

public interface AverageRatesService {
    Optional<RateDto> calculateAverageRatesByMonthAndYear(long currencyId, short month, int year);
}
