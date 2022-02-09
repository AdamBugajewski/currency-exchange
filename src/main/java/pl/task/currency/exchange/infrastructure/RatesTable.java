package pl.task.currency.exchange.infrastructure;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

public class RatesTable {

    private final LocalDate forDay;
    private final List<RateStored> rates;

    public RatesTable(LocalDate forDay, List<RateStored> rates) {
        this.forDay = forDay;
        this.rates = rates;
    }

    Optional<RateStored> findRate(Currency source, Currency destination) {
        return rates.stream().filter(
                rateStored -> rateStored.getSource().equals(source) && rateStored.getDestination().equals(destination)).findFirst();
    }

    public LocalDate getForDay() {
        return forDay;
    }
}
