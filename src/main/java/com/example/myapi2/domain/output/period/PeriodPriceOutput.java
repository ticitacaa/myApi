package com.example.myapi2.domain.output.period;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "시세 데이터")
public class PeriodPriceOutput {

    @Schema(description = "날짜", example = "20240416")
    private String locdt;
    @Schema(description = "시가", example = "156.7420")
    private String openprc;
    @Schema(description = "고가", example = "156.7420")
    private String highprc;
    @Schema(description = "저가", example = "156.7420")
    private String lowprc;
    @Schema(description = "종가", example = "156.7420")
    private String closeprc;
    @Schema(description = "거래량", example = "96999956")
    private String volume;
}
