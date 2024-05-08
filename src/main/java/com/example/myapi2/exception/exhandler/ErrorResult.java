package com.example.myapi2.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResult {

    private int status;
    private String message;
}
