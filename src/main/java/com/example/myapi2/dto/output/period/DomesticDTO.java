package com.example.myapi2.dto.output.period;

import com.example.myapi2.domain.output.period.PeriodPriceOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "국내 조회 응답 데이터")
public class DomesticDTO {
    @Schema(description = "상태코드", example = "200")
    private int status;
    @Schema(description = "거래소", example = "KSE", allowableValues = {"KSE, KDQ"})
    private String excd;
    @Schema(description = "심볼", example = "KI.001", maxLength = 16)
    private String symbol;
    @Schema(description = "한글명", example = "코스피")
    private String hname;
    @Schema(description = "총합")
    private int total;
    @Schema(description = "데이터")
    private List<PeriodPriceOutput> data;
}
