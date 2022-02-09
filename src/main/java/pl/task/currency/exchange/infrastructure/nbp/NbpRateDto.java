package pl.task.currency.exchange.infrastructure.nbp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpRateDto {

    private final String code;
    private final BigDecimal mid;

    @JsonCreator
    public NbpRateDto(@JsonProperty("code") String code, @JsonProperty("mid") BigDecimal mid) {
        this.code = code;
        this.mid = mid;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getMid() {
        return mid;
    }

}
