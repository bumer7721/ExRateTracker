package co.spribe.test.db.services.impl;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.entities.ExchangeRate;
import co.spribe.test.db.repositiries.ExchangeRateRepository;
import co.spribe.test.db.services.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Service
public class ExchangeRateServiceImpl extends BaseServiceImpl<ExchangeRate> implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    protected ExchangeRateRepository getRepository() {
        return exchangeRateRepository;
    }

    @Override
    public Map<Currency, ExchangeRate> getExchangeRateByQuote(String base, List<Currency> quotes) {
        return exchangeRateRepository.findByBaseAndQuoteIn(base, quotes).stream()
                .collect(toMap(ExchangeRate::getQuote, identity()));
    }
}
