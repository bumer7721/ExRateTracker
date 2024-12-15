package co.spribe.test.facades;

import co.spribe.test.facades.data.CurrencyDTO;
import co.spribe.test.facades.data.ExchangeRateDTO;

import java.util.List;
import java.util.Optional;

public interface CurrencyFacade {

    List<CurrencyDTO> getAllCurrencies();

    CurrencyDTO addCurrency(CurrencyDTO currency);

    Optional<ExchangeRateDTO> getExchangeRate(String currency);
}
