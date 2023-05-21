package by.fin.service.impl;

import by.fin.service.RemoteRatesService;
import by.fin.service.dto.RateDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@Service
public class RemoteRatesServiceImpl implements RemoteRatesService {
    private final static String REMOTE_SERVICE_URL = "https://api.nbrb.by/exrates/rates/dynamics/";

    private final RestTemplate restTemplate;

    public RemoteRatesServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<RateDto> fetchRatesByCurrencyIdAndDates(Long currencyId, LocalDate start, LocalDate end) {
        final String START_PARAM = "startdate";
        final String END_PARAM = "enddate";

        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(REMOTE_SERVICE_URL + currencyId)
                .queryParam(START_PARAM, start)
                .queryParam(END_PARAM, end);

        ResponseEntity<List<RateDto>> rates = restTemplate.exchange(
                uri.toUriString(), HttpMethod.GET,null, new ParameterizedTypeReference<>() {}
        );

        return rates.getBody();
    }
}
