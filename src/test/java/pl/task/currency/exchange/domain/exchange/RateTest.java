package pl.task.currency.exchange.domain.exchange;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class RateTest {

    private final static Currency USD = Currency.getInstance("USD");
    private final static Currency EUR = Currency.getInstance("EUR");
    private final static Currency PLN = Currency.getInstance("PLN");

    @Test
    void shouldExchangeSimpleMoney() {
        // given
        Rate euroRate = new Rate(PLN, EUR, new BigDecimal("4"));

        // when
        Money actual = euroRate.exchange(new Money(new BigDecimal("40"), PLN));

        // then
        assertThat(actual.getAmount()).isEqualTo(new BigDecimal("10.00"));
        assertThat(actual.getCurrency()).isEqualTo(EUR);
    }

    @Test
    void shouldExchangeWeirdRate() {
        // given
        Rate euroRate = new Rate(PLN, EUR, new BigDecimal("4.5467"));

        // when
        Money actual = euroRate.exchange(new Money(new BigDecimal("47.89"), PLN));

        // then
        assertThat(actual.getAmount()).isEqualTo(new BigDecimal("10.53"));
        assertThat(actual.getCurrency()).isEqualTo(EUR);
    }

    @Test
    void shouldExchangeSmallRate() {
        // given
        Rate euroRate = new Rate(PLN, EUR, new BigDecimal("0.345"));

        // when
        Money actual = euroRate.exchange(new Money(new BigDecimal("47.89"), PLN));

        // then
        assertThat(actual.getAmount()).isEqualTo(new BigDecimal("138.81"));
        assertThat(actual.getCurrency()).isEqualTo(EUR);
    }

    @Test
    void shouldThrowExceptionWhenWrongCurrency() {
        // given
        Rate euroRate = new Rate(PLN, EUR, new BigDecimal("4.5439"));

        // when
        assertThatThrownBy(() -> euroRate.exchange(new Money(new BigDecimal("500.00"), USD)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Source currency of rate: PLN is different than given one: USD");

    }

}