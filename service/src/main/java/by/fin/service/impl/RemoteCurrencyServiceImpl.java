package by.fin.service.impl;

import by.fin.service.RemoteCurrencyService;
import by.fin.service.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RemoteCurrencyServiceImpl implements RemoteCurrencyService {
    private final static String REMOTE_SERVICE_URL = "https://api.nbrb.by/exrates/currencies/";

    private final RestTemplate restTemplate;

    public RemoteCurrencyServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
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
