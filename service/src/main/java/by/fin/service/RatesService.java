package by.fin.service;

import by.fin.service.dto.RateDto;

import java.time.LocalDate;
import java.util.List;

public interface RatesService {
    List<RateDto> addRates(Long currencyId, List<RateDto> newRates);
    List<RateDto> findRatesByCurrencyId(Long currencyId);
    List<RateDto> findRatesByCurrencyIdBetween(Long currencyId, LocalDate start, LocalDate end);
}
