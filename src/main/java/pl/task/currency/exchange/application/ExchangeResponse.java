package pl.task.currency.exchange.application;

import java.math.BigDecimal;

public class ExchangeResponse {

    private BigDecimal amount;
    private String currency;

    public ExchangeResponse(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
