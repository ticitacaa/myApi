package com.example.myapi2.controller;

import com.example.myapi2.exception.ErrorCode;
import com.example.myapi2.exception.exhandler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(BindException e) {
        String detail = "";
        for (FieldError fieldError : e.getFieldErrors()) {
            detail = fieldError.getDefaultMessage();
        }

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.FIELD_ERROR);
        errorResponse.setDetail(detail);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidExcdException.class)
    public ResponseEntity<ErrorResponse> invalidExcdException(InvalidExcdException e) {

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_EXCD_PARAMETER);
        errorResponse.setDetail("KSE, KDQ 중 하나를 입력해주세요");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOverExcdException.class)
    public ResponseEntity<ErrorResponse> invalidOverExcdException(InvalidOverExcdException e) {

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_EXCD_PARAMETER);
        errorResponse.setDetail("TSE,HKS,SHS,SZS,NYS,NAS,AMS,FRX 중 하나를 입력해주세요");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueryFailedException.class)
    public ResponseEntity<ErrorResponse> queryFailedException(QueryFailedException e) {

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.DATA_NOT_FOUND);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
