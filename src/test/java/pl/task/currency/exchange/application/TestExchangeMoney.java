package pl.task.currency.exchange.application;

import pl.task.currency.exchange.domain.exchange.Money;

public class TestExchangeMoney implements ExchangeMoneyFacade {

    private Money money;
    private String accountNotExist;
    private String rateForAccountNotExist;


    @Override
    public Money exchangeMoney(String accountNumber) throws AccountDoesNotExistException, RateForCurrencyDoesNotExistException {
        if (accountNumber.equals(accountNotExist)) {
            throw new AccountDoesNotExistException("Test Exception");
        } else if (accountNumber.equals(rateForAccountNotExist)) {
            throw new RateForCurrencyDoesNotExistException("Test Rates");
        } else {
            return money;
        }
    }

    public void setAccountNotExist(String accountNotExist) {
        this.accountNotExist = accountNotExist;
    }

    public void setRateForAccountNotExist(String rateForAccountNotExist) {
        this.rateForAccountNotExist = rateForAccountNotExist;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
