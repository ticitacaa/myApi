package com.example.myapi2.service.input.sql;

import com.example.myapi2.domain.input.period.PeriodInput;
import com.example.myapi2.service.input.PeriodInputService;

public class PeriodInputServiceSqlImpl implements PeriodInputService<PeriodInput> {

    @Override
    public PeriodInput makeInput(PeriodInput periodInput) {
        return periodInput;
    }
}
