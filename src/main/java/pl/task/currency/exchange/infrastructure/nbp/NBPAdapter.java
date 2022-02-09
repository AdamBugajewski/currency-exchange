package pl.task.currency.exchange.infrastructure.nbp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.task.currency.exchange.infrastructure.RateStored;
import pl.task.currency.exchange.infrastructure.RatesTable;
import pl.task.currency.exchange.infrastructure.RatesTableConnector;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NBPAdapter implements RatesTableConnector {

    private final RestTemplate restTemplate;

    public NBPAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<RatesTable> fetchCurrent() {
        Optional<NbpRatesTableDto> table = fetchTable();
        if (table.isPresent()) {
            NbpRatesTableDto current = table.get();
            List<RateStored> rates = current.getRates().stream().map(
                            nbpRateDto -> new RateStored(
                                    Currency.getInstance("PLN"),
                                    Currency.getInstance(nbpRateDto.getCode()),
                                    nbpRateDto.getMid()))
                    .collect(Collectors.toList());
            return Optional.of(new RatesTable(LocalDate.parse(current.getEffectiveDate()), rates));
        } else {
            return Optional.empty();
        }
    }

    private Optional<NbpRatesTableDto> fetchTable() {
        NbpRatesTableDto[] responseList;
        try {
            responseList = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/A", NbpRatesTableDto[].class);
            if (responseList == null || responseList.length < 1) {
                return Optional.empty();
            }
            return Optional.of(Stream.of(responseList).findFirst().orElseThrow(() -> new NoNbpTablesFoundException("No NBP tables were found.")));
        } catch (RestClientException | NoNbpTablesFoundException exception) {
            return Optional.empty();
        }
    }
}
