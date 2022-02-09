package pl.task.currency.exchange.domain.exchange;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Rate {

    private final Currency source;
    private final Currency destination;
    private final BigDecimal rate;

    public Rate(Currency source, Currency destination, BigDecimal rate) {
        this.source = source;
        this.destination = destination;
        this.rate = rate;
    }

    Money exchange(Money money) {
        if (money.sameAs(source)) {
            return new Money(money.getAmount().divide(rate, RoundingMode.HALF_EVEN), destination);
        } else {
            throw new IllegalArgumentException(String.format("Source currency of rate: %s is different than given one: %s", source, money.getCurrency()));
        }
    }
}
