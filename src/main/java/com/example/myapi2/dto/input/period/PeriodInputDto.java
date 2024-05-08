package com.example.myapi2.dto.input.period;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "일주월 기간 조회 요청")
public class PeriodInputDto {

    //@Schema(description = "지연 구분 필드", allowableValues = "1")
    //private String delaygb = "1";

    @Schema(description = "거래소 아이디 필드", example = "KSE", allowableValues = {"KSE, KDQ"})
    @NotBlank(message = "'excd' 필드는 공백일 수 없습니다.")
    @Pattern(regexp = "[a-zA-Z]{3}", message = "'excd' 필드는 반드시 영문 3자여야 합니다.")
    private String excd;

    @Schema(description = "심볼 필드", example = "KI.001")
    @NotBlank(message = "'symbol' 필드는 공백일 수 없습니다.")
    private String symbol;

    @Schema(description = "기간 구분 필드", example = "1", allowableValues = {"1, 2, 3"})
    @Pattern(regexp = "[123]", message = "'kindgb' 필드는 반드시 1,2,3 중 하나만 입력해주세요.")
    private String kindgb = "1";

    @Schema(description = "분", example = "1")
    @Pattern(regexp = "^\\d*$", message = "'timegap' 필드는 숫자만 입력 가능합니다.")
    private String timegap = "1";

    @Schema(description = "생년월일(yyyyMMdd)", example = "20240422")
    @Pattern(regexp = "^$|^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$", message = "'locdt' 필드는 YYYYMMDD 형식이거나 비어 있어야 합니다.")
    private String locdt = "";

    @Schema(description = "수정주가 필드", example = "1", allowableValues = {"1,2"})
    @Pattern(regexp = "[12]", message = "'adjgb' 필드는 반드시 1,2 중 하나만 입력해주세요.")
    private String adjgb = "1";

    @Schema(description = "조회 카운트 필드", example = "10")
    @Pattern(regexp = "^\\d*$", message = "'count' 필드는 숫자만 입력 가능합니다.")
    private String count = "10";
}
