package pl.task.currency.exchange.infrastructure.nbp;

public class NoNbpTablesFoundException extends Exception {

    public NoNbpTablesFoundException(String errorMessage) {
        super(errorMessage);
    }

}
