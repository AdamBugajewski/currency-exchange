package pl.task.currency.exchange.application;

import org.junit.jupiter.api.Test;
import pl.task.currency.exchange.domain.account.Account;
import pl.task.currency.exchange.domain.exchange.Exchange;
import pl.task.currency.exchange.domain.exchange.Money;
import pl.task.currency.exchange.domain.exchange.RateDatabase;
import pl.task.currency.exchange.infrastructure.InMemoryAccountRepository;
import pl.task.currency.exchange.infrastructure.InMemoryRateRepository;
import pl.task.currency.exchange.infrastructure.RateStored;
import pl.task.currency.exchange.infrastructure.RatesTable;
import pl.task.currency.exchange.infrastructure.TestTableConnector;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ExchangeMoneyServiceTest {

    private final static Currency PLN = Currency.getInstance("PLN");
    private final static Currency EUR = Currency.getInstance("EUR");
    private final static Currency USD = Currency.getInstance("USD");

    InMemoryAccountRepository inMemoryAccountRepository = new InMemoryAccountRepository();
    TestTableConnector testTableConnector = new TestTableConnector();
    RateDatabase rateDatabase = new InMemoryRateRepository(testTableConnector);
    Exchange exchange = new Exchange(rateDatabase);

    ExchangeMoneyService service = new ExchangeMoneyService(inMemoryAccountRepository, exchange);

    @Test
    void shouldExchangeMoney() throws Exception {
        // given
        inMemoryAccountRepository.addAccount(new Account("123", new BigDecimal("12"), PLN));
        testTableConnector.addRatesTable(new RatesTable(LocalDate.now(), Collections.singletonList(
                new RateStored(PLN, EUR, new BigDecimal("3")))));

        // when
        Money actual = service.exchangeMoney("123");

        // then
        assertThat(actual.getAmount()).isEqualTo(new BigDecimal("4.00"));
        assertThat(actual.getCurrency()).isEqualTo(EUR);
    }

    @Test
    void shouldThrowExceptionWhenThereIsNoRate() throws Exception {
        // given
        inMemoryAccountRepository.addAccount(new Account("123", new BigDecimal("12"), PLN));
        testTableConnector.addRatesTable(new RatesTable(LocalDate.now(), Collections.singletonList(
                new RateStored(PLN, USD, new BigDecimal("2.5")))));

        // when
        assertThatThrownBy(() -> service.exchangeMoney("123"))
                .isInstanceOf(RateForCurrencyDoesNotExistException.class)
                        .hasMessage("Cannot find a rate for given currency");

    }

    @Test
    void shouldThrowExceptionWhenThereIsNoAccount() throws Exception {
        // when
        assertThatThrownBy(() -> service.exchangeMoney("123"))
                .isInstanceOf(AccountDoesNotExistException.class)
                .hasMessage("Account not exist in database");
    }

}