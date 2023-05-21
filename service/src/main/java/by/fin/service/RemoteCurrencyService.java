package by.fin.service;

import by.fin.service.dto.CurrencyDto;

import java.util.Optional;

public interface RemoteCurrencyService {
    Optional<CurrencyDto> findCurrencyById(Long currencyId);
}
