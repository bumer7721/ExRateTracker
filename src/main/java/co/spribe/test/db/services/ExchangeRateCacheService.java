package co.spribe.test.db.services;

import co.spribe.test.db.entities.ExchangeRate;

import java.util.Optional;

public interface ExchangeRateCacheService extends ExchangeRateService {

    Optional<ExchangeRate> getExchangeRate(String currency);
}
