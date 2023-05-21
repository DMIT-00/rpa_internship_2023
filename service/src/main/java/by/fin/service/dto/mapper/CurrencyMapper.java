package by.fin.service.dto.mapper;

import by.fin.repository.entity.Currency;
import by.fin.service.dto.CurrencyDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyMapper {
    public static Currency fromDto(CurrencyDto currencyDto) {
        return new Currency(
                currencyDto.getId(),
                currencyDto.getAbbreviation(),
                currencyDto.getName(),
                currencyDto.getScale(),
                new ArrayList<>()
        );
    }

    public static CurrencyDto toDto(Currency currency) {
        return new CurrencyDto(
                currency.getId(),
                currency.getName(),
                currency.getAbbreviation(),
                currency.getScale()
        );
    }

    public static List<Currency> fromDto(List<CurrencyDto> currencyDto) {
        return currencyDto
                .stream()
                .map(CurrencyMapper::fromDto)
                .collect(Collectors.toList());
    }

    public static List<CurrencyDto> toDto(List<Currency> currency) {
        return currency
                .stream()
                .map(CurrencyMapper::toDto)
                .collect(Collectors.toList());
    }
}
