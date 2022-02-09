package pl.task.currency.exchange.domain.exchange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

public interface RateDatabase {

    Optional<BigDecimal> rateFor(Currency source, Currency destination, LocalDate date);

}
