package com.example.myapi2.service.input.ap;

import com.example.myapi2.domain.input.period.PeriodInput;
import com.example.myapi2.service.input.PeriodInputService;
import org.springframework.stereotype.Service;

@Service
public class PeriodInputServiceApImpl implements PeriodInputService<String> {

    @Override
    public String makeInput(PeriodInput periodInput) {
        return periodInput.format();
    }
}
