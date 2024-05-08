package com.example.myapi2.exception.exhandler;

import com.example.myapi2.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Setter
@Schema(description = "에러 정보")
public class ErrorResponse {

    private String errorCode;
    private String message;
    private String detail = "";

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
