package by.fin.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {
    @JsonProperty("Cur_ID")
    private Long id;

    @JsonProperty("Cur_Name_Eng")
    private String name;

    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;

    @JsonProperty("Cur_Scale")
    private Long scale;
}
