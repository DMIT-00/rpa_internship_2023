package by.fin.service.impl;

import by.fin.repository.CurrencyRepository;
import by.fin.repository.entity.Currency;
import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.mapper.CurrencyMapper;
import by.fin.service.exception.AlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CurrencyServiceImplTest {
    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCurrency() {
        // Given
        CurrencyDto newCurrency = new CurrencyDto(1L, "Dollar", "USD", 1L);
        Currency currency = CurrencyMapper.fromDto(newCurrency);

        when(currencyRepository.existsById(currency.getId())).thenReturn(false);
        when(currencyRepository.save(any())).thenReturn(currency);

        // When
        CurrencyDto result = currencyService.addCurrency(newCurrency);

        // Then
        assertNotNull(result);
        assertEquals(newCurrency.getId(), result.getId());
    }

    @Test
    void testAddCurrency_ThrowsAlreadyExistsException() {
        // Given
        CurrencyDto newCurrency = new CurrencyDto(1L, "Dollar", "USD", 1L);

        when(currencyRepository.existsById(newCurrency.getId())).thenReturn(true);

        // When
        // Then
        assertThrows(AlreadyExistsException.class, () -> {
            currencyService.addCurrency(newCurrency);
        });
    }

    @Test
    void testFindAll() {
        // Given
        List<Currency> currencies = Arrays.asList(
                new Currency(1L, "USD", "Dollar", 1L, new ArrayList<>()),
                new Currency(2L, "EUR", "Euro", 1L, new ArrayList<>())
        );

        when(currencyRepository.findAll()).thenReturn(currencies);

        // When
        List<CurrencyDto> result = currencyService.findAll();

        // Then
        assertNotNull(result);
        assertEquals(currencies.size(), result.size());
    }

    @Test
    void testFindCurrencyById() {
        // Given
        Currency currency = new Currency(1L, "USD", "Dollar", 1L, new ArrayList<>());

        when(currencyRepository.findById(currency.getId())).thenReturn(Optional.of(currency));

        // When
        Optional<CurrencyDto> result = currencyService.findCurrencyById(currency.getId());

        // Then
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(currency.getId(), result.get().getId());
    }

    @Test
    void testFindCurrencyById_NotFound() {
        // Given
        Long currencyId = 1L;
        when(currencyRepository.findById(currencyId)).thenReturn(Optional.empty());

        // When
        Optional<CurrencyDto> result = currencyService.findCurrencyById(currencyId);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindByAbbreviation() {
        // Given
        String abbreviation = "USD";
        List<Currency> currencies = Arrays.asList(
                new Currency(1L, abbreviation, "Dollar", 1L, new ArrayList<>())
        );

        when(currencyRepository.findByAbbreviation(abbreviation)).thenReturn(currencies);

        // When
        List<CurrencyDto> result = currencyService.findByAbbreviation(abbreviation);

        // Then
        assertNotNull(result);
        assertEquals(currencies.size(), result.size());
    }
}