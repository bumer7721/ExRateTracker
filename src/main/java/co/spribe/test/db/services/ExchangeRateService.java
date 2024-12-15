package co.spribe.test.db.services;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.entities.ExchangeRate;

import java.util.List;
import java.util.Map;

public interface ExchangeRateService extends BaseService<ExchangeRate> {

    Map<Currency, ExchangeRate> getExchangeRateByQuote(String base, List<Currency> quotes);
}
