package co.spribe.test.db.repositiries;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.entities.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ExchangeRateRepositoryTest {

    public static final String USD_CODE = "USD";
    public static final String UAH_CODE = "UAH";
    public static final String EUR_CODE = "EUR";

    public static final Double USD_UAH_RATE = 1.0;
    public static final Double USD_EUR_RATE = 2.0;
    public static final Double EUR_UAH_RATE = 3.0;


    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ExchangeRateRepository unit;

    @Test
    public void shouldFindByBaseAndQuote() {

        //given
        var uah = new Currency();
        uah.setCode(UAH_CODE);
        uah = currencyRepository.save(uah);

        var eur = new Currency();
        eur.setCode(EUR_CODE);
        eur = currencyRepository.save(eur);

        var usdUAHrate = new ExchangeRate();
        usdUAHrate.setBase(USD_CODE);
        usdUAHrate.setQuote(uah);
        usdUAHrate.setRate(USD_UAH_RATE);

        var usdEURrate = new ExchangeRate();
        usdEURrate.setBase(USD_CODE);
        usdEURrate.setQuote(eur);
        usdEURrate.setRate(USD_EUR_RATE);

        var eurUAHrate = new ExchangeRate();
        eurUAHrate.setBase(EUR_CODE);
        eurUAHrate.setQuote(uah);
        eurUAHrate.setRate(EUR_UAH_RATE);

        unit.saveAll(List.of(usdUAHrate, usdEURrate, eurUAHrate));

        //when
        var usdRates = unit.findByBaseAndQuoteIn(USD_CODE, List.of(uah, eur));

        //then
        assertThat(usdRates).isNotEmpty().hasSize(2);
        assertThat(usdRates).extracting(ExchangeRate::getBase)
                .containsExactly(USD_CODE, USD_CODE);
        assertThat(usdRates).extracting(ExchangeRate::getQuote)
                .containsExactlyInAnyOrder(uah, eur);
        assertThat(usdRates).extracting(ExchangeRate::getRate)
                .containsExactlyInAnyOrder(USD_UAH_RATE, USD_EUR_RATE);
    }

}