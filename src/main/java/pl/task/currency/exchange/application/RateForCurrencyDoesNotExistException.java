package pl.task.currency.exchange.application;

public class RateForCurrencyDoesNotExistException extends Exception {

    public RateForCurrencyDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }

}
