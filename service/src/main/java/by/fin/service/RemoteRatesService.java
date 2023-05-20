package by.fin.service;

import by.fin.service.dto.RateDto;

import java.time.LocalDate;
import java.util.List;

public interface RemoteRatesService {
    List<RateDto> fetchRatesByCurrencyIdAndDates(Long currencyId, LocalDate start, LocalDate end);
}
