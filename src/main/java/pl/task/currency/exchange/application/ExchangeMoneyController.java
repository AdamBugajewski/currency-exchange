package pl.task.currency.exchange.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.task.currency.exchange.domain.exchange.Money;

@RestController
public class ExchangeMoneyController {

    private final ExchangeMoneyFacade exchangeMoneyFacade;

    public ExchangeMoneyController(ExchangeMoneyFacade exchangeMoneyFacade) {
        this.exchangeMoneyFacade = exchangeMoneyFacade;
    }

    @GetMapping("/exchange/{accountNumber}")
    public ResponseEntity<ExchangeResponse> exchangeMoney(@PathVariable("accountNumber") String accountNumber) {
        try {
            Money exchanged = exchangeMoneyFacade.exchangeMoney(accountNumber);
            return ResponseEntity.ok(new ExchangeResponse(exchanged.getAmount(), exchanged.getCurrency().getCurrencyCode()));
        } catch (AccountDoesNotExistException | RateForCurrencyDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
