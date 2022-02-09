package pl.task.currency.exchange.infrastructure;

import org.springframework.stereotype.Repository;
import pl.task.currency.exchange.domain.exchange.RateDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRateRepository implements RateDatabase {

    private final Map<LocalDate, RatesTable> rateStore = new HashMap<>();

    private final RatesTableConnector ratesTableConnector;

    public InMemoryRateRepository(RatesTableConnector ratesTableConnector) {
        this.ratesTableConnector = ratesTableConnector;
    }

    @Override
    public Optional<BigDecimal> rateFor(Currency source, Currency destination, LocalDate date) {
        if (rateStore.containsKey(date)) {
            return rateStore.get(date)
                    .findRate(source, destination).map(RateStored::getRate);
        } else {
            Optional<RatesTable> ratesTable = ratesTableConnector.fetchCurrent();
            ratesTable.ifPresent(table -> rateStore.put(table.getForDay(), table));
            return todayOrYesterday(source, destination, date);
        }
    }

    private Optional<BigDecimal> todayOrYesterday(Currency source, Currency destination, LocalDate date) {
        if (rateStore.containsKey(date)) {
            return rateStore.get(date)
                    .findRate(source, destination).map(RateStored::getRate);
        } else if (rateStore.containsKey(date.minusDays(1))){
            return rateStore.get(date.minusDays(1))
                    .findRate(source, destination).map(RateStored::getRate);
        } else {
            return Optional.empty();
        }
    }
}
