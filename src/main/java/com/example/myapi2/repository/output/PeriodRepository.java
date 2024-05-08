package com.example.myapi2.repository.output;

import com.example.myapi2.domain.output.period.PeriodOutput;

public interface PeriodRepository {

    PeriodOutput findData(String exid, String symbol, Object input);

}
