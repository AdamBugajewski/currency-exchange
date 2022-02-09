package pl.task.currency.exchange.infrastructure;

import java.math.BigDecimal;
import java.util.Currency;

public class RateStored {

    private final Currency source;
    private final Currency destination;
    private final BigDecimal rate;

    public RateStored(Currency source, Currency destination, BigDecimal rate) {
        this.source = source;
        this.destination = destination;
        this.rate = rate;
    }

    public Currency getSource() {
        return source;
    }

    public Currency getDestination() {
        return destination;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
