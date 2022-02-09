package pl.task.currency.exchange.domain.exchange;

import org.springframework.stereotype.Service;
import pl.task.currency.exchange.application.RateForCurrencyDoesNotExistException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Service
public class Exchange {

    private final RateDatabase rateDatabase;

    public Exchange(RateDatabase rateDatabase) {
        this.rateDatabase = rateDatabase;
    }

    public Money exchange(Money toExchange, Currency toCurrency) throws RateForCurrencyDoesNotExistException {
        BigDecimal value = rateDatabase.rateFor(toExchange.getCurrency(), toCurrency, LocalDate.now())
                .orElseThrow(() -> new RateForCurrencyDoesNotExistException("Cannot find a rate for given currency"));
        return new Rate(toExchange.getCurrency(), toCurrency, value).exchange(toExchange);
    }

}
