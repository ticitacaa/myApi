package com.example.myapi2.service.call.period;

import com.example.myapi2.domain.input.period.PeriodInput;
import com.example.myapi2.dto.input.period.PeriodInputDto;
import com.example.myapi2.domain.output.period.PeriodOutput;
import com.example.myapi2.dto.output.period.PeriodOutputDto;
import com.example.myapi2.service.input.PeriodInputService;
import com.example.myapi2.service.output.PeriodOutputService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PeriodCallServiceImpl implements PeriodCallService {

    private final PeriodInputService<?> periodInputService;
    private final PeriodOutputService periodOutputService;

    @Override
    public PeriodOutputDto call(PeriodInputDto periodInputDto) {

        PeriodInput periodInput = PeriodInput.toEntity(periodInputDto);
        log.info("INPUT : {}", periodInput);
        Object input = periodInputService.makeInput(periodInput);
        PeriodOutput periodOutput = periodOutputService.lookUpData(periodInput.getExid(), periodInput.getSymbol(), input);

        return PeriodOutputDto.toDTO(periodOutput);
    }
}
