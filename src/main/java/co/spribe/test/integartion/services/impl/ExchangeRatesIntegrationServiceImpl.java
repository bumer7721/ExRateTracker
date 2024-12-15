package co.spribe.test.integartion.services.impl;

import co.spribe.test.integartion.data.ExchangeRateResponseData;
import co.spribe.test.integartion.services.ExchangeRateIntegrationService;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static co.spribe.test.integartion.IntegrationConstants.*;

@Service
public class ExchangeRatesIntegrationServiceImpl implements ExchangeRateIntegrationService {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeRatesIntegrationServiceImpl.class);

    public static final String USD = "USD";
    public static final String UAH = "UAH";

    private final String latestEndpoint;
    private final RestTemplate restTemplate;

    public ExchangeRatesIntegrationServiceImpl(@Value("${openexchangerates.url.latest}") String latestEndpoint,
                                               RestTemplate restTemplate) {
        this.latestEndpoint = latestEndpoint;
        this.restTemplate = restTemplate;
    }

    @Override
    @Retry(name = "getExchangeRate", fallbackMethod = "fallbackWithRandomRate")
    public ExchangeRateResponseData getExchangeRate(List<String> quotes) {
        var urlWithSymbols = UriComponentsBuilder.fromUriString(latestEndpoint)
                .queryParam(SYMBOLS_QUERY_PARAM, String.join(SYMBOLS_DELIMITER, quotes))
                .toUriString();
        return restTemplate.getForObject(urlWithSymbols, ExchangeRateResponseData.class);
    }

    public ExchangeRateResponseData fallbackWithRandomRate(List<String> quotes, Throwable ex) {
        LOG.warn("Returned falback response with random rate", ex);
        var response = new ExchangeRateResponseData();

        response.setBase(USD);
        response.setRates(Map.of(UAH, ThreadLocalRandom.current().nextDouble(2, 40)));
        return response;
    }
}
