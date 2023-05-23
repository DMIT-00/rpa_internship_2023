package by.fin.service.impl;

import by.fin.repository.RatesRepository;
import by.fin.repository.entity.Currency;
import by.fin.repository.entity.Rate;
import by.fin.service.CurrencyService;
import by.fin.service.RatesService;
import by.fin.service.RemoteCurrencyService;
import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.RateDto;
import by.fin.service.dto.mapper.CurrencyMapper;
import by.fin.service.dto.mapper.RateMapper;
import by.fin.service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class RatesServiceImpl implements RatesService {
    private final RatesRepository ratesRepository;
    private final CurrencyService currencyService;
    private final RemoteCurrencyService remoteCurrencyService;

    @Override
    public List<RateDto> findRatesByCurrencyId(Long currencyId) {
        return RateMapper.toDto(ratesRepository.findByCurrency_Id(currencyId));
    }

    @Override
    public List<RateDto> findRatesByCurrencyIdBetween(Long currencyId, LocalDate start, LocalDate end) {
        return RateMapper.toDto(ratesRepository.findByCurrency_IdAndDateBetween(currencyId, start, end));
    }

    @Override
    @Transactional
    public List<RateDto> addRates(Long currencyId, List<RateDto> newRates) {
        List<Rate> rates = RateMapper.fromDto(newRates);

        Optional<CurrencyDto> currencyDto = currencyService.findCurrencyById(currencyId);
        if (currencyDto.isEmpty()) {
            currencyDto = remoteCurrencyService.findCurrencyById(currencyId);

            currencyService.addCurrency(currencyDto
                    .orElseThrow(() -> new NotFoundException("Currency not found with id " + currencyId)));
        }

        Currency currency = CurrencyMapper.fromDto(currencyDto
                .orElseThrow(() -> new NotFoundException("Currency not found with id " + currencyId))
        );

        rates.forEach(currency::addRate);

        log.info("Adding rates. Size = {}", rates.size());

        return RateMapper.toDto(ratesRepository.saveAll(rates));
    }
}
