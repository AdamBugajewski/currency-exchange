package pl.task.currency.exchange.application;

public class AccountDoesNotExistException extends Exception {

    public AccountDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }

}
