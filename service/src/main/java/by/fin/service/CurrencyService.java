package by.fin.service;

import by.fin.service.dto.CurrencyDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    CurrencyDto addCurrency(CurrencyDto currency);
    List<CurrencyDto> findAll();
    Optional<CurrencyDto> findCurrencyById(Long currencyId);
    List<CurrencyDto> findByAbbreviation(String abbreviation);
}
