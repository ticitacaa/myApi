package com.example.myapi2.service.output;

import com.example.myapi2.domain.output.period.PeriodOutput;

public interface PeriodOutputService {

    PeriodOutput lookUpData(String exid, String symbol, Object input);
}
