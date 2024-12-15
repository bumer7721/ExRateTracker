package co.spribe.test.cronjobs;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.entities.ExchangeRate;
import co.spribe.test.db.services.CurrencyService;
import co.spribe.test.db.services.ExchangeRateCacheService;
import co.spribe.test.integartion.services.ExchangeRateIntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class RetrieveExchangeRateCronJob {

    private static final Logger LOG = LoggerFactory.getLogger(RetrieveExchangeRateCronJob.class);

    private final CurrencyService currencyService;
    private final ExchangeRateIntegrationService exchangeRateIntegrationService;
    private final ExchangeRateCacheService exchangeRateCacheService;

    public RetrieveExchangeRateCronJob(CurrencyService currencyService,
                                       ExchangeRateIntegrationService exchangeRateIntegrationService,
                                       ExchangeRateCacheService exchangeRateCacheService) {
        this.currencyService = currencyService;
        this.exchangeRateIntegrationService = exchangeRateIntegrationService;
        this.exchangeRateCacheService = exchangeRateCacheService;
    }

    @Scheduled(cron = "${retrieve.exchange.rate.cron}")
    public void perform() {
        LOG.info("Retrieving exchange rate cron job");
        receiveExchangeRates();
        LOG.info("Retrieved exchange rate SUCCESSFULLY!!!");
    }

    @Transactional
    protected void receiveExchangeRates() {
        var quotes = currencyService.findAll();

        var symbols = quotes.stream()
                .map(Currency::getCode)
                .toList();
        var rateResponseData = exchangeRateIntegrationService.getExchangeRate(symbols);
        var baseCurrency = rateResponseData.getBase();
        var externalExchangeRates = rateResponseData.getRates();

        var exchangeRates = exchangeRateCacheService.getExchangeRateByQuote(baseCurrency, quotes);

        var ratesForUpdate = new LinkedList<ExchangeRate>();
        for (Currency quote: quotes) {
            var exchangeRate = exchangeRates.get(quote);
            if (isNull(exchangeRate)) {
                exchangeRate = new ExchangeRate();
                exchangeRate.setBase(baseCurrency);
                exchangeRate.setQuote(quote);
            }

            var externalRate = externalExchangeRates.get(quote.getCode());
            if (nonNull(externalRate)) {
                    exchangeRate.setRate(externalRate);
                    ratesForUpdate.add(exchangeRate);
            }
        }

        exchangeRateCacheService.saveAll(ratesForUpdate);
    }
}
