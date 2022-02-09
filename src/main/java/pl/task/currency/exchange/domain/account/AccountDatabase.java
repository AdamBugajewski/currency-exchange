package pl.task.currency.exchange.domain.account;

import java.util.Optional;

public interface AccountDatabase {

    Optional<Account> findBy(String number);

}
