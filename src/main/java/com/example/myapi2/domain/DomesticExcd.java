package com.example.myapi2.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "일주월 기간 조회2")
public enum DomesticExcd {
    KSE, KDQ;
}
