package by.fin.service.dto.mapper;

import by.fin.repository.entity.Currency;
import by.fin.repository.entity.Rate;
import by.fin.service.dto.RateDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RateMapper {
    public static Rate fromDto(RateDto rateDto) {
        return new Rate(
                null,
                new Currency(rateDto.getCurrencyId(), null, null, null, new ArrayList<>()),
                rateDto.getDate(),
                rateDto.getRate()
        );
    }

    public static RateDto toDto(Rate rate) {
        return new RateDto(rate.getCurrency().getId(), rate.getDate(), rate.getRate());
    }

    public static List<Rate> fromDto(List<RateDto> rateDto) {
        return rateDto
                .stream()
                .map(RateMapper::fromDto)
                .collect(Collectors.toList());
    }

    public static List<RateDto> toDto(List<Rate> rate) {
        return rate
                .stream()
                .map(RateMapper::toDto)
                .collect(Collectors.toList());
    }
}
