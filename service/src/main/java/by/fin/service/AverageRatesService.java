package by.fin.service;

import by.fin.repository.entity.Rate;
import by.fin.service.dto.RateDto;

import java.time.LocalDate;

public interface AverageRatesService {
    RateDto calculateAverageRatesByMonthAndYear(long currencyId, short month, int year);
}
