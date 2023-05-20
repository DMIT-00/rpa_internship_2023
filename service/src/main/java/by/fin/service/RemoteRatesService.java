package by.fin.service;

import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.RateDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RemoteRatesService {
    Optional<CurrencyDto> fetchCurrencyByAbbreviation(String currencyAbbreviation);
    List<RateDto> fetchRatesByCurrencyAndDates(Long currencyId, LocalDate start, LocalDate end);
}
