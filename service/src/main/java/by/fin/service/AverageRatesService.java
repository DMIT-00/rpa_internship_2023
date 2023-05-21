package by.fin.service;

import by.fin.service.dto.RateDto;

public interface AverageRatesService {
    RateDto calculateAverageRatesByMonthAndYear(long currencyId, short month, int year);
}
