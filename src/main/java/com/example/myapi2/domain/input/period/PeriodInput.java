package com.example.myapi2.domain.input.period;

import com.example.myapi2.dto.input.period.PeriodInputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodInput {

    private String delaygb;
    private String exid;
    private String symbol;
    private String kindgb;
    private String timegap;
    private String locdt;
    private String adjgb;
    private String count;

    public static PeriodInput toEntity(PeriodInputDto dto) {
        return PeriodInput.builder()
                .delaygb("1")
                .exid(excdToExid(dto.getExcd()))
                .symbol(dto.getSymbol())
                .kindgb(dto.getKindgb())
                .timegap(dto.getTimegap())
                .locdt(dto.getLocdt())
                .adjgb(dto.getAdjgb())
                .count(dto.getCount())
                .build();
    }

    public String format() {
        return String.format("%-1s%-3s%-16s%-1s%-3s%-8s%-1s%-5s",
                delaygb, exid, symbol, kindgb, timegap, locdt, adjgb, count);
    }

    private static String excdToExid(String excd) {
        String exid = "";
        if(excd.equals("KSE")) exid = "1";
        if(excd.equals("KDQ")) exid = "2";
        if(excd.equals("NYS")) exid = "21";
        if(excd.equals("NAS")) exid = "22";
        if(excd.equals("AMS")) exid = "23";

        return exid;
    }

}
