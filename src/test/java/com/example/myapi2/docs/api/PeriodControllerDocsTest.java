package com.example.myapi2.docs.api;


import com.example.myapi2.Constants;
import com.example.myapi2.controller.PeriodController;
import com.example.myapi2.docs.RestDocsTest;
import com.example.myapi2.domain.output.period.PeriodPriceOutput;
import com.example.myapi2.dto.input.period.PeriodInputDto;
import com.example.myapi2.dto.output.period.PeriodOutputDto;
import com.example.myapi2.service.call.period.PeriodCallService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PeriodControllerDocsTest extends RestDocsTest {

    private final PeriodCallService periodCallService = mock(PeriodCallService.class);

    @Override
    protected Object initializeController() {
        return new PeriodController(periodCallService);
    }

    @DisplayName("국내 시세 조회 API")
    @Test
    void getData() throws Exception {


        // given
        given(periodCallService.call(any()))
                .willReturn(new PeriodOutputDto(
                        200,
                        "KSE",
                        "KI.001",
                        "코스피",
                        10,
                        List.of(new PeriodPriceOutput(
                                        "20240416",
                                        "156.7420",
                                        "156.7420",
                                        "156.7420",
                                        "156.7420",
                                        "96999956"
                                )
                        )
                ));


        // when
        String url = "/api/"+ Constants.API_VERSION + "/domestic-stock/quotations/dailyprice";



        mockMvc.perform(RestDocumentationRequestBuilders.get(url)
                    .param("excd", "KSE")
                    .param("symbol", "KI.001"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("get-datas",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        queryParameters(
                                parameterWithName("excd")
                                        .description("거래소 번호"),
                                parameterWithName("symbol")
                                        .description("심볼")),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER)
                                        .description("상태 코드"),
                                fieldWithPath("excd").type(JsonFieldType.STRING)
                                        .description("거래소"),
                                fieldWithPath("symbol").type(JsonFieldType.STRING)
                                        .description("심볼"),
                                fieldWithPath("hname").type(JsonFieldType.STRING)
                                        .description("한글명"),
                                fieldWithPath("total").type(JsonFieldType.NUMBER)
                                        .description("총합"),
                                fieldWithPath("data[].locdt").type(JsonFieldType.STRING)
                                        .description("날짜"),
                                fieldWithPath("data[].openprc").type(JsonFieldType.STRING)
                                        .description("시가"),
                                fieldWithPath("data[].highprc").type(JsonFieldType.STRING)
                                        .description("고가"),
                                fieldWithPath("data[].lowprc").type(JsonFieldType.STRING)
                                        .description("저가"),
                                fieldWithPath("data[].closeprc").type(JsonFieldType.STRING)
                                        .description("종가"),
                                fieldWithPath("data[].volume").type(JsonFieldType.STRING)
                                        .description("거래량")

                        ))
                );
    }
}
