package by.fin.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateDto {
    @JsonProperty("Cur_ID")
    private Long currencyId;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Cur_OfficialRate")
    private BigDecimal rate;
}
