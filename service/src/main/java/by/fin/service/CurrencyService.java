package by.fin.service;

import by.fin.service.dto.CurrencyDto;

import java.util.Optional;

public interface CurrencyService {
    CurrencyDto addCurrency(CurrencyDto currency);
    Optional<CurrencyDto> findCurrencyById(Long currencyId);
}
