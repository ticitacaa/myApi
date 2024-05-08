package com.example.myapi2.dto.output.period;

import com.example.myapi2.domain.output.period.PeriodOutput;
import com.example.myapi2.domain.output.period.PeriodPriceOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "일주월 기간 조회 응답")
public class PeriodOutputDto {

    @Schema(description = "상태코드", example = "200")
    private int status;
    @Schema(description = "거래소", allowableValues = {"KSE, KDQ"})
    private String excd;
    @Schema(description = "심볼", example = "KI.001", maxLength = 16)
    private String symbol;
    @Schema(description = "한글명", example = "코스피")
    private String hname;
    @Schema(description = "총합")
    private int total;
    @Schema(description = "데이터")
    private List<PeriodPriceOutput> data;

    public static PeriodOutputDto toDTO(PeriodOutput entity) {
        return PeriodOutputDto.builder()
                .excd(exidToExcd(entity.getExid()))
                .symbol(entity.getSymbol())
                .hname(entity.getHname())
                .total(entity.getData().size())
                .data(entity.getData())
                .build();
    }

    private static String exidToExcd(String exid) {
        String excd = "";

        if(exid.equals("1")) excd = "KSE";
        if(exid.equals("2")) excd = "KDQ";
        if(exid.equals("21")) excd = "NYS";
        if(exid.equals("22")) excd = "NAS";
        if(exid.equals("23")) excd = "AMS";

        return excd;
    }
}
