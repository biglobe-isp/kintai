package com.naosim.dddwork.kintai.datasource.csv.entity;

import lombok.Data;

import java.util.List;

@Data
public class RegulatedBreakTimeEntities {
    List<RegulatedBreakTimeEntity> records;

    public RegulatedBreakTimeEntities(List<RegulatedBreakTimeEntity> records) {
        if (records == null) {
            throw new IllegalArgumentException("出勤記録リストがありません。");
        }
        this.records = records;
    }
}
