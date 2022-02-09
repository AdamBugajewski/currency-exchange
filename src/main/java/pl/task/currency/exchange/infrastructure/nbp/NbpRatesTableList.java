package pl.task.currency.exchange.infrastructure.nbp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpRatesTableList {

    private List<NbpRatesTableDto> tables;

    public NbpRatesTableList() {}

    public List<NbpRatesTableDto> getTables() {
        return tables;
    }

    public void setTables(List<NbpRatesTableDto> tables) {
        this.tables = tables;
    }
}
