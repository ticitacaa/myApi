package com.example.myapi2.domain.output.period;

import lombok.Getter;

@Getter
public enum PeriodIndexes {

    LOCDT(8), OPENPRC(9), HIGHPRC(9), LOWPRC(9), CLOSEPRC(9), VOLUME(15);

    private final int length;

    PeriodIndexes(int length) {
        this.length = length;
    }

}
