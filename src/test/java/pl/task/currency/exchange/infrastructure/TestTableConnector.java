package pl.task.currency.exchange.infrastructure;

import java.util.Optional;

public class TestTableConnector implements RatesTableConnector {

    RatesTable ratesTable;

    @Override
    public Optional<RatesTable> fetchCurrent() {
        return Optional.ofNullable(ratesTable);
    }

    public void addRatesTable(RatesTable ratesTable) {
        this.ratesTable = ratesTable;
    }
}
