package com.example.myapi2.domain.output.period;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PeriodOutput {

    private String exid;
    private String symbol;
    private String hname;
    private List<PeriodPriceOutput> data = new ArrayList<>();
}
