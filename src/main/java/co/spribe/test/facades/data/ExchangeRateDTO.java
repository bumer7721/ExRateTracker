package co.spribe.test.facades.data;

import java.util.Objects;

public class ExchangeRateDTO {

    private String base;
    private String quote;
    private Double rate;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, quote, rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var exchangeRate = (ExchangeRateDTO) o;
        return Objects.equals(base, exchangeRate.base) &&
                Objects.equals(quote, exchangeRate.quote) &&
                Objects.equals(rate, exchangeRate.rate);
    }
}
