package co.spribe.test.db.services.impl;

import co.spribe.test.db.entities.ExchangeRate;
import co.spribe.test.db.repositiries.ExchangeRateRepository;
import co.spribe.test.db.services.ExchangeRateCacheService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;

@Service
public class ExchangeRateCacheServiceImpl extends ExchangeRateServiceImpl implements ExchangeRateCacheService {

    private final Map<String, ExchangeRate> exchangeRateCache = new ConcurrentHashMap<>();

    public ExchangeRateCacheServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        super(exchangeRateRepository);
    }

    @PostConstruct
    public void initializeCache() {
        refreshCache();
    }

    @Override
    public List<ExchangeRate> saveAll(List<ExchangeRate> rates) {
        var savedRates = super.saveAll(rates);
        updateCache(savedRates);
        return savedRates;
    }

    private void refreshCache() {
        var rates = getRepository().findAll();
        exchangeRateCache.clear();
        updateCache(rates);
    }

    private void updateCache(List<ExchangeRate> rates) {
        for (ExchangeRate rate : rates) {
            exchangeRateCache.put(rate.getQuote().getCode(), rate);
        }
    }

    @Override
    public Optional<ExchangeRate> getExchangeRate(String currency) {
        return ofNullable(exchangeRateCache.get(currency));
    }
}
