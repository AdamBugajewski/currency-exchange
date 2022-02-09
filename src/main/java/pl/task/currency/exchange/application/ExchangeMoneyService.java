package pl.task.currency.exchange.application;

import org.springframework.stereotype.Service;
import pl.task.currency.exchange.domain.account.Account;
import pl.task.currency.exchange.domain.account.AccountDatabase;
import pl.task.currency.exchange.domain.exchange.Exchange;
import pl.task.currency.exchange.domain.exchange.Money;

import java.util.Currency;

@Service
public class ExchangeMoneyService implements ExchangeMoneyFacade {

    private final AccountDatabase accountDatabase;
    private final Exchange exchange;

    public ExchangeMoneyService(AccountDatabase accountDatabase, Exchange exchange) {
        this.accountDatabase = accountDatabase;
        this.exchange = exchange;
    }

    public Money exchangeMoney(String accountNumber) throws AccountDoesNotExistException, RateForCurrencyDoesNotExistException {
        Account account = accountDatabase.findBy(accountNumber).orElseThrow(() -> new AccountDoesNotExistException("Account not exist in database"));
        return exchange.exchange(new Money(account.getAmount(), account.getCurrency()), Currency.getInstance("EUR"));
    }

}
