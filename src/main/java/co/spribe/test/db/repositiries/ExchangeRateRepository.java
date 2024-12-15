package co.spribe.test.db.repositiries;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.entities.ExchangeRate;

import java.util.List;

public interface ExchangeRateRepository extends BaseRepository<ExchangeRate> {

    List<ExchangeRate> findByBaseAndQuoteIn(String base, List<Currency> quotes);

}
