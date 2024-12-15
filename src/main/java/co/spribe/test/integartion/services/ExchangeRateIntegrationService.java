package co.spribe.test.integartion.services;

import co.spribe.test.integartion.data.ExchangeRateResponseData;

import java.util.List;

public interface ExchangeRateIntegrationService {

    ExchangeRateResponseData getExchangeRate(List<String> quotes);
}
