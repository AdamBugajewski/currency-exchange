package pl.task.currency.exchange.infrastructure.nbp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpRatesTableDto {

    private final String table;
    private final String effectiveDate;
    private final List<NbpRateDto> rates;

    @JsonCreator
    public NbpRatesTableDto(@JsonProperty("table") String table,
                            @JsonProperty("effectiveDate") String effectiveDate,
                            @JsonProperty("rates") List<NbpRateDto> rates) {
        this.table = table;
        this.effectiveDate = effectiveDate;
        this.rates = rates;
    }

    public String getTable() {
        return table;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public List<NbpRateDto> getRates() {
        return rates;
    }

}
