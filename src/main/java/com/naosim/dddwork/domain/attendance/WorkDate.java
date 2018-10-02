package com.naosim.dddwork.domain.attendance;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 出勤日
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class WorkDate {
    @Getter
    private final LocalDate value;

    // 20181002 仕様変更 ADD START
    // 仕様変更 来月の途中から休憩時間が増える
    public boolean is20181115Later() {

        return this.getValue().isAfter(LocalDate.of(2018, 11, 14));
    }
    // 20181002 仕様変更 ADD END
}
