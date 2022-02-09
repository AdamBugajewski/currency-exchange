package pl.task.currency.exchange.domain.account;

import java.math.BigDecimal;
import java.util.Currency;

public class Account {

    private final String number;
    private final BigDecimal amount;
    private final Currency currency;

    public Account(String number, BigDecimal amount, Currency currency) {
        this.number = number;
        this.amount = amount;
        this.currency = currency;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
