package com.example.myapi2.controller;

import com.example.myapi2.Constants;
import com.example.myapi2.domain.DomesticExcd;
import com.example.myapi2.domain.OverseasExcd;
import com.example.myapi2.dto.input.period.PeriodInputDto;
import com.example.myapi2.dto.output.period.DomesticDTO;
import com.example.myapi2.dto.output.period.OverseasDTO;
import com.example.myapi2.dto.output.period.PeriodOutputDto;
import com.example.myapi2.exception.exhandler.ErrorResponse;
import com.example.myapi2.exception.exhandler.InvalidExcdException;
import com.example.myapi2.exception.exhandler.InvalidOverExcdException;
import com.example.myapi2.service.call.period.PeriodCallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;


@Tag(name = "시세조회", description = "국내외 시세 조회 관련 API")
@Slf4j
@RestController
@RequestMapping("/api/"+ Constants.API_VERSION)
@RequiredArgsConstructor
public class PeriodController {

    private final PeriodCallService periodCallService;

    @GetMapping("/domestic-stock/quotations/dailyprice")
    @Operation(
            summary = "국내 일간 조회",
            description = "특정 거래소 및 심볼을 통해 국내 일주월 데이터를 반환합니다.")
    @Parameters({
            @Parameter(name = "excd", description = "거래소", example = "KSE", required = true),
            @Parameter(name = "symbol", description = "심볼", example = "KI.001", required = true),
            @Parameter(name = "kindgb", description = "구분", example = "1" ),
            @Parameter(name = "locdt", description = "날짜", example = "20240430"),
            @Parameter(name = "adjgb", description = "수정주가", example = "1"),
            @Parameter(name = "count", description = "카운트", example = "10")
    })
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "정상 처리되었습니다.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DomesticDTO.class))),
            @ApiResponse(responseCode = "API-001", description = "요청 파라미터 형식을 확인해주세요.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "API-002", description = "조회에 실패하였습니다.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "API-003", description = "유효하지 않은 excd 값입니다.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    public ResponseEntity<PeriodOutputDto> domesticDailyCall(@Parameter(hidden = true) @Valid PeriodInputDto periodInputDto) {

        try {
            DomesticExcd.valueOf(periodInputDto.getExcd());
        } catch (Exception e) {
            throw new InvalidExcdException("excd 필드 값을 확인해주세요.");
        }

        PeriodOutputDto periodOutputDto = periodCallService.call(periodInputDto);
        periodOutputDto.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(periodOutputDto);
    }

    @GetMapping("/overseas-price/quotations/dailyprice")
    @Operation(
            summary = "해외 일간 조회",
            description = "특정 거래소 및 심볼을 통해 해외 일주월 데이터를 반환합니다.")
    @Parameters({
            @Parameter(name = "excd", description = "거래소", example = "NAS", required = true),
            @Parameter(name = "symbol", description = "심볼", example = "TSLA", required = true),
            @Parameter(name = "kindgb", description = "구분", example = "1" ),
            @Parameter(name = "locdt", description = "날짜", example = "20240430"),
            @Parameter(name = "adjgb", description = "수정주가", example = "1"),
            @Parameter(name = "count", description = "카운트", example = "10")
    })
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "정상 처리되었습니다.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = OverseasDTO.class))),
            @ApiResponse(responseCode = "API-001", description = "요청 파라미터 형식을 확인해주세요.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "API-002", description = "조회에 실패하였습니다.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "API-003", description = "유효하지 않은 excd 값입니다.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    ))
    })
    public ResponseEntity<PeriodOutputDto> overseasDailyCall(@Parameter(hidden = true) @Valid PeriodInputDto periodInputDto) {

        try {
            OverseasExcd.valueOf(periodInputDto.getExcd());
        } catch (Exception e) {
            throw new InvalidOverExcdException("excd 필드 값을 확인해주세요.");
        }

        PeriodOutputDto periodOutputDto = periodCallService.call(periodInputDto);
        periodOutputDto.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(periodOutputDto);
    }

}
