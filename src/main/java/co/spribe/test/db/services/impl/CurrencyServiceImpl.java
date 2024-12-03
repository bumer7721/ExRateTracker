package co.spribe.test.db.services.impl;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.repositiries.BaseRepository;
import co.spribe.test.db.repositiries.CurrencyRepository;
import co.spribe.test.db.services.CurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl extends BaseServiceImpl<Currency> implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    protected BaseRepository<Currency> getRepository() {
        return currencyRepository;
    }
}
