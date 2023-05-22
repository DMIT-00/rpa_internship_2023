package by.fin.service.impl;

import by.fin.service.RemoteCurrencyService;
import by.fin.service.dto.CurrencyDto;
import by.fin.service.dto.RateDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RemoteCurrencyServiceImpl implements RemoteCurrencyService {
    private final static String REMOTE_SERVICE_URL = "https://api.nbrb.by/exrates/currencies/";

    private final RestTemplate restTemplate;

    public RemoteCurrencyServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<CurrencyDto> findAll() {
        ResponseEntity<List<CurrencyDto>> currencies = restTemplate.exchange(
                REMOTE_SERVICE_URL, HttpMethod.GET,null, new ParameterizedTypeReference<>() {}
        );
        return currencies.getBody();
    }

    @Override
    public List<CurrencyDto> findByAbbreviation(String abbreviation) {
        List<CurrencyDto> currencies = findAll();

        return currencies.stream()
                .filter(cur -> abbreviation.equals(cur.getAbbreviation()))
                .toList();
    }

    @Override
    public Optional<CurrencyDto> findCurrencyById(Long currencyId) {
        ResponseEntity<CurrencyDto> response = restTemplate.getForEntity(
                REMOTE_SERVICE_URL + currencyId,
                CurrencyDto.class
        );

        return Optional.ofNullable(response.getBody());
    }
}
