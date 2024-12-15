package co.spribe.test.db.services.impl;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.entities.ExchangeRate;
import co.spribe.test.db.repositiries.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {

    public static final String USD_CODE = "USD";

    @Mock
    private Currency currencyUah;
    @Mock
    private Currency currencyEur;
    @Mock
    private ExchangeRate rateUah;
    @Mock
    private ExchangeRate rateEur;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateServiceImpl unit;

    @Test
    void shouldGetExchangeRateByQuote() {
        var quotes = List.of(currencyUah, currencyEur);
        when(rateUah.getQuote()).thenReturn(currencyUah);
        when(rateEur.getQuote()).thenReturn(currencyEur);
        when(exchangeRateRepository.findByBaseAndQuoteIn(USD_CODE, quotes)).thenReturn(List.of(rateUah, rateEur));

        var actualResult = unit.getExchangeRateByQuote(USD_CODE, quotes);

        assertThat(actualResult).hasSize(quotes.size())
                .containsOnlyKeys(currencyUah, currencyEur)
                .containsValues(rateUah, rateEur);
    }
}