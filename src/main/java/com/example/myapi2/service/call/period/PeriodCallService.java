package com.example.myapi2.service.call.period;

import com.example.myapi2.dto.input.period.PeriodInputDto;
import com.example.myapi2.dto.output.period.PeriodOutputDto;

public interface PeriodCallService {
    public PeriodOutputDto call(PeriodInputDto periodInputDto);

}
