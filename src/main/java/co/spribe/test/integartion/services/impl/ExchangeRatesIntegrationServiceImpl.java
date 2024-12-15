package co.spribe.test.integartion.services.impl;

import co.spribe.test.integartion.data.ExchangeRateResponseData;
import co.spribe.test.integartion.services.ExchangeRateIntegrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static co.spribe.test.integartion.IntegrationConstants.*;

@Service
public class ExchangeRatesIntegrationServiceImpl implements ExchangeRateIntegrationService {

    private final String latestEndpoint;
    private final RestTemplate restTemplate;

    public ExchangeRatesIntegrationServiceImpl(@Value("${openexchangerates.url.latest}") String latestEndpoint,
                                               RestTemplate restTemplate) {
        this.latestEndpoint = latestEndpoint;
        this.restTemplate = restTemplate;
    }

    @Override
    public ExchangeRateResponseData getExchangeRate(List<String> quotes) {
        var urlWithSymbols = UriComponentsBuilder.fromUriString(latestEndpoint)
                .queryParam(SYMBOLS_QUERY_PARAM, String.join(SYMBOLS_DELIMITER, quotes))
                .toUriString();
        return restTemplate.getForObject(urlWithSymbols, ExchangeRateResponseData.class);
    }

}
