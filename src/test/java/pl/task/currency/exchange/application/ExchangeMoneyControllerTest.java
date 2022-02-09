package pl.task.currency.exchange.application;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.task.currency.exchange.domain.exchange.Money;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

class ExchangeMoneyControllerTest {

    private final Currency USD = Currency.getInstance("USD");

    TestExchangeMoney testExchangeMoney = new TestExchangeMoney();
    ExchangeMoneyController controller = new ExchangeMoneyController(testExchangeMoney);

    @Test
    void shouldResponseWithExchangeMoney() {
        testExchangeMoney.setMoney(new Money(new BigDecimal("10.00"), USD));

        ResponseEntity<ExchangeResponse> actual = controller.exchangeMoney("any account");

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isNotNull();
        assertThat(actual.getBody().getAmount()).isEqualTo(new BigDecimal("10.00"));
        assertThat(actual.getBody().getCurrency()).isEqualTo(USD.getCurrencyCode());
    }

    @Test
    void shouldResponseWithErrorForNotExistingAccount() {
        testExchangeMoney.setAccountNotExist("453");

        ResponseEntity<ExchangeResponse> actual = controller.exchangeMoney("453");

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(actual.getBody()).isNull();
    }

    @Test
    void shouldResponseWithErrorForNotExistingRate() {
        testExchangeMoney.setRateForAccountNotExist("675");

        ResponseEntity<ExchangeResponse> actual = controller.exchangeMoney("675");

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(actual.getBody()).isNull();
    }

}