package co.spribe.test.db.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate extends BaseEntity {

    @Column(nullable = false, length = 3)
    private String base;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_currency_id", nullable = false)
    private Currency quote;

    @Column(nullable = false)
    private Double rate;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Currency getQuote() {
        return quote;
    }

    public void setQuote(Currency quote) {
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
        return Objects.hash(super.hashCode(), base, quote, rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var exchangeRate = (ExchangeRate) o;
        return super.equals(exchangeRate) &&
                Objects.equals(base, exchangeRate.base) &&
                Objects.equals(quote, exchangeRate.quote) &&
                Objects.equals(rate, exchangeRate.rate);
    }
}
