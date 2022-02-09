package pl.task.currency.exchange.application;

import pl.task.currency.exchange.domain.exchange.Money;

public interface ExchangeMoneyFacade {

    Money exchangeMoney(String accountNumber) throws AccountDoesNotExistException, RateForCurrencyDoesNotExistException;

}
