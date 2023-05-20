package by.fin.service.impl;

import by.fin.service.RemoteRatesService;
import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.RateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RemoteRatesServiceImpl implements RemoteRatesService {
    private final String REMOTE_SERVICE_URL = "https://api.nbrb.by/exrates/";

    @Override
    public List<RateDto> fetchRatesByCurrencyIdAndDates(Long currencyId, LocalDate start, LocalDate end) {
        final String SERVICE_URL = REMOTE_SERVICE_URL + "rates/dynamics/";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<RateDto>> rates = restTemplate.exchange(
                SERVICE_URL + currencyId + "?startdate=" + start.toString() + "&enddate=" + end.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return rates.getBody();
    }
}
