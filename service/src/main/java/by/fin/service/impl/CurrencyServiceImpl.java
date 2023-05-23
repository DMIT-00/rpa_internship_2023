package by.fin.service.impl;

import by.fin.repository.CurrencyRepository;
import by.fin.repository.entity.Currency;
import by.fin.service.CurrencyService;
import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.mapper.CurrencyMapper;
import by.fin.service.exception.AlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Override
    @Transactional
    public CurrencyDto addCurrency(CurrencyDto newCurrency) {
        Currency currency = CurrencyMapper.fromDto(newCurrency);

        if (currency.getId() != null && currencyRepository.existsById(currency.getId()))
            throw new AlreadyExistsException("Currency already exists! Id: " + currency.getId());

        return CurrencyMapper.toDto(currencyRepository.save(currency));
    }

    @Override
    public List<CurrencyDto> findAll() {
        return CurrencyMapper.toDto(currencyRepository.findAll());
    }

    @Override
    public Optional<CurrencyDto> findCurrencyById(Long currencyId) {
        Optional<Currency> currency = currencyRepository.findById(currencyId);

        return currency.map(CurrencyMapper::toDto);
    }

    @Override
    public List<CurrencyDto> findByAbbreviation(String abbreviation) {
        return CurrencyMapper.toDto(currencyRepository.findByAbbreviation(abbreviation));
    }
}
