package by.fin.service.impl;

import by.fin.repository.RatesRepository;
import by.fin.repository.entity.Currency;
import by.fin.repository.entity.Rate;
import by.fin.service.CurrencyService;
import by.fin.service.RemoteCurrencyService;
import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.RateDto;
import by.fin.service.dto.mapper.RateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class RatesServiceImplTest {
    @Mock
    private RatesRepository ratesRepository;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private RemoteCurrencyService remoteCurrencyService;

    @InjectMocks
    private RatesServiceImpl ratesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindRatesByCurrencyId() {
        // Given
        Currency currency = new Currency(1L, "USD", "Dollar", 1L, new ArrayList<>());
        List<Rate> rates = Arrays.asList(
                new Rate(currency, LocalDate.of(2023, 5, 7), BigDecimal.valueOf(0.1)),
                new Rate(currency, LocalDate.of(2023, 5, 8), BigDecimal.valueOf(0.2))
        );

        when(ratesRepository.findByCurrency_Id(currency.getId())).thenReturn(rates);

        // When
        List<RateDto> result = ratesService.findRatesByCurrencyId(currency.getId());

        // Then
        assertEquals(rates.size(), result.size());
        verify(ratesRepository).findByCurrency_Id(currency.getId());
    }

    @Test
    public void testFindRatesByCurrencyIdBetween() {
        // Given
        LocalDate start = LocalDate.of(2023, 5, 7);
        LocalDate end = LocalDate.of(2023, 5, 8);
        Currency currency = new Currency(1L, "USD", "Dollar", 1L, new ArrayList<>());
        List<Rate> rates = Arrays.asList(
                new Rate(currency, start, BigDecimal.valueOf(0.1)),
                new Rate(currency, end, BigDecimal.valueOf(0.2))
        );

        when(ratesRepository.findByCurrency_IdAndDateBetween(currency.getId(), start, end)).thenReturn(rates);

        // When
        List<RateDto> result = ratesService.findRatesByCurrencyIdBetween(currency.getId(), start, end);

        // Then
        assertEquals(rates.size(), result.size());
        verify(ratesRepository).findByCurrency_IdAndDateBetween(currency.getId(), start, end);
    }

    @Test
    public void testAddRates() {
        // Given
        CurrencyDto currency = new CurrencyDto(1L, "Dollar", "USD", 1L);
        List<RateDto> newRates = Arrays.asList(
                new RateDto(currency.getId(), LocalDate.of(2023, 5, 1), BigDecimal.valueOf(2.4325)),
                new RateDto(currency.getId(), LocalDate.of(2023, 5, 2), BigDecimal.valueOf(2.4336)),
                new RateDto(currency.getId(), LocalDate.of(2023, 5, 3), BigDecimal.valueOf(2.4336)),
                new RateDto(currency.getId(), LocalDate.of(2023, 5, 4), BigDecimal.valueOf(2.4336))
        );

        when(currencyService.findCurrencyById(currency.getId())).thenReturn(Optional.of(currency));
        when(ratesRepository.saveAll(anyList())).thenReturn(RateMapper.fromDto(newRates));

        // When
        List<RateDto> result = ratesService.addRates(currency.getId(), newRates);

        // Then
        assertEquals(newRates.size(), result.size());
        verify(currencyService).findCurrencyById(currency.getId());
        verify(ratesRepository).saveAll(anyList());
        verifyNoMoreInteractions(currencyService, ratesRepository);
    }
}