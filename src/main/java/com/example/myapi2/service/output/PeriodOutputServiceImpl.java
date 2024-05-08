package com.example.myapi2.service.output;

import com.example.myapi2.domain.output.period.PeriodOutput;
import com.example.myapi2.repository.output.PeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeriodOutputServiceImpl implements PeriodOutputService {

    private final PeriodRepository periodRepository;

    @Override
    public PeriodOutput lookUpData(String exid, String symbol, Object input) {
        return periodRepository.findData(exid, symbol, input);
    }
}
