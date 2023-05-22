package by.fin.service;

import by.fin.service.dto.CurrencyDto;

import java.util.List;
import java.util.Optional;

public interface RemoteCurrencyService {
    List<CurrencyDto> findAll();
    List<CurrencyDto> findByAbbreviation(String abbreviation);
    Optional<CurrencyDto> findCurrencyById(Long currencyId);
}
