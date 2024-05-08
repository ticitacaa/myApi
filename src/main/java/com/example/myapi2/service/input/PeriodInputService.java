package com.example.myapi2.service.input;

import com.example.myapi2.domain.input.period.PeriodInput;

public interface PeriodInputService<T> {

    public T makeInput(PeriodInput periodInput);
}
