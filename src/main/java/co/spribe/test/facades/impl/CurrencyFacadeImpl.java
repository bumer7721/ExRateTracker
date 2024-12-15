package co.spribe.test.facades.impl;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.services.CurrencyService;
import co.spribe.test.db.services.ExchangeRateCacheService;
import co.spribe.test.facades.CurrencyFacade;
import co.spribe.test.facades.data.CurrencyDTO;
import co.spribe.test.facades.data.ExchangeRateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CurrencyFacadeImpl implements CurrencyFacade {

    private final ModelMapper modelMapper;
    private final CurrencyService currencyService;
    private final ExchangeRateCacheService exchangeRateCacheService;

    public CurrencyFacadeImpl(CurrencyService currencyService,
                              ModelMapper modelMapper,
                              ExchangeRateCacheService exchangeRateCacheService) {
        this.currencyService = currencyService;
        this.modelMapper = modelMapper;
        this.exchangeRateCacheService = exchangeRateCacheService;
    }

    @Override
    public List<CurrencyDTO> getAllCurrencies() {
        return currencyService.findAll().stream()
                .map(c -> modelMapper.map(c, CurrencyDTO.class))
                .toList();
    }

    @Override
    public CurrencyDTO addCurrency(CurrencyDTO currency) {
        var newCurrency = currencyService.save(modelMapper.map(currency, Currency.class));
        return modelMapper.map(newCurrency, CurrencyDTO.class);
    }

    @Override
    public Optional<ExchangeRateDTO> getExchangeRate(String currency) {
        return exchangeRateCacheService.getExchangeRate(currency)
                .map(r -> modelMapper.map(r, ExchangeRateDTO.class));
    }
}
