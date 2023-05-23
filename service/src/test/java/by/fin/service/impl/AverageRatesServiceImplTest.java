package by.fin.service.impl;

import by.fin.service.RatesService;
import by.fin.service.WeekendService;
import by.fin.service.dto.RateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class AverageRatesServiceImplTest {

    @Mock
    private RatesService ratesService;

    @Mock
    private WeekendService weekendService;

    private AverageRatesServiceImpl averageRatesService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        averageRatesService = new AverageRatesServiceImpl(ratesService, weekendService);
    }

    @Test
    public void testCalculateAverageRatesByMonthAndYear() {
        // Given
        long currencyId = 1L;
        short month = 5;
        int year = 2023;

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<RateDto> rates = Arrays.asList(
                new RateDto(currencyId, LocalDate.of(2023, 5, 1), BigDecimal.valueOf(2.4325)),
                new RateDto(currencyId, LocalDate.of(2023, 5, 2), BigDecimal.valueOf(2.4336)),
                new RateDto(currencyId, LocalDate.of(2023, 5, 3), BigDecimal.valueOf(2.4336)),
                new RateDto(currencyId, LocalDate.of(2023, 5, 4), BigDecimal.valueOf(2.4336)),
                new RateDto(currencyId, LocalDate.of(2023, 5, 5), BigDecimal.valueOf(9999))
        );
        BigDecimal expectedResult = BigDecimal.valueOf(2.4333249533746635);

        List<LocalDate> weekends = Arrays.asList(
                LocalDate.of(2023, 5, 7),
                LocalDate.of(2023, 5, 14),
                LocalDate.of(2023, 5, 21),
                LocalDate.of(2023, 5, 28),
                LocalDate.of(2023, 5, 5)
        );

        // Mock dependencies
        when(ratesService.findRatesByCurrencyIdBetween(currencyId, start, end)).thenReturn(rates);
        when(weekendService.findAllWeekendDatesBetween(start, end)).thenReturn(weekends);

        // When
        Optional<RateDto> result = averageRatesService.calculateAverageRatesByMonthAndYear(currencyId, month, year);

        // Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expectedResult, result.get().getRate());

        verify(ratesService, times(1)).findRatesByCurrencyIdBetween(currencyId, start, end);
        verify(weekendService, times(1)).findAllWeekendDatesBetween(start, end);
    }
}