package pl.task.currency.exchange.infrastructure;

import java.util.Optional;

public interface RatesTableConnector {

    Optional<RatesTable> fetchCurrent();

}
