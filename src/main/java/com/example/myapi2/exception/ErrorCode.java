package com.example.myapi2.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    FIELD_ERROR("API-001", "요청 파라미터를 확인해주세요."),
    DATA_NOT_FOUND("API-002", "조회에 실패하였습니다."),
    INVALID_EXCD_PARAMETER("API-003", "유효하지 않은 excd 값입니다."),

    ;

    private final String code;
    private final String message;
}
