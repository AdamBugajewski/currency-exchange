package pl.task.currency.exchange.infrastructure;

import org.springframework.stereotype.Repository;
import pl.task.currency.exchange.domain.account.Account;
import pl.task.currency.exchange.domain.account.AccountDatabase;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryAccountRepository implements AccountDatabase {

    private final static Currency PLN = Currency.getInstance("PLN");
    private final Map<String, Account> accountStore = new HashMap<>();

    @PostConstruct
    public void start() {
        addAccount(new Account("123", new BigDecimal("103.33"), PLN));
        addAccount(new Account("456", new BigDecimal("50.12"), PLN));
        addAccount(new Account("789", new BigDecimal("22.45"), PLN));
    }

    public void addAccount(Account account) {
        accountStore.put(account.getNumber(), account);
    }

    @Override
    public Optional<Account> findBy(String number) {
        return Optional.ofNullable(accountStore.get(number));
    }
}
