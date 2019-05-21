package com.naosim.dddwork.kintai.domain.model.timerecord;

import com.naosim.dddwork.kintai.domain.core.type.time.stamp.RecordTimestamp;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.time.range.WorkTimeRange;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.ActualUnpaidWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.ActualWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.PaidWorkedTime;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;


/**
 * 日別勤務時間（Entity）
 */
@ToString
@Getter
public class DailyWorkedTime {

//TODO: @ID とかにしたい
//INSPECT: lombokだとうまくいかなかった...使い方間違っている？
//    @EqualsAndHashCode.Include
    final AttendanceDate attendanceDate;

    /* 日別拘束時間 */
    final DailyTimeRecord dailyTimeRecord;

    /* ［導出］労働時間（賃金発生分） */
    final PaidWorkedTime paidWorkedTime;
    /* ［導出］サービス残業時間 */
    final ActualUnpaidWorkedTime actualUnpaidWorkedTime;

    /* タイムスタンプ */
    final RecordTimestamp recordTimestamp = RecordTimestamp.now();


    /* 生成 */

    private DailyWorkedTime(DailyTimeRecord dailyTimeRecord) {

        attendanceDate = dailyTimeRecord.attendanceDate;
        this.dailyTimeRecord = dailyTimeRecord;

        /* 拘束時間 */
        final WorkTimeRange spentTimeRange = dailyTimeRecord.spentTimeRange;
        /* 実際の労働時間 */
        final ActualWorkedTime actualWorkedTime = ActualWorkedTime.of(spentTimeRange);

        actualUnpaidWorkedTime = ActualUnpaidWorkedTime.of(spentTimeRange);
        paidWorkedTime = PaidWorkedTime.of(actualWorkedTime, actualUnpaidWorkedTime);
    }

    public static DailyWorkedTime of(DailyTimeRecord dailyTimeRecord) {
        return new DailyWorkedTime(dailyTimeRecord);
    }


    /* equals&hashCode */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyWorkedTime that = (DailyWorkedTime) o;
        return attendanceDate.equals(that.attendanceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceDate);
    }
}
