package com.naosim.dddwork.domain.duty;

import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkTime;
import com.naosim.dddwork.domain.word.WorkingHour;
import com.naosim.dddwork.domain.word.WorkingMinute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * 各種時間の計算
 */
@ToString(includeFieldNames = false)
public class CalculateTime {

    /**
     * 出勤時間
     */
    @Getter
    @Setter
    private WorkTime workTimeFrom;

    /**
     * 退勤時間
     */
    @Getter
    @Setter
    private WorkTime workTimeTo;

    /**
     * 出勤時間（時）
     */
    @Getter
    @Setter
    private WorkingHour fromHour;

    /**
     * 出勤時間（分）
     */
    @Getter
    @Setter
    private WorkingMinute fromMinute;

    /**
     * 退勤時間（時）
     */
    @Getter
    @Setter
    private WorkingHour toHour;

    /**
     * 退勤時間（分）
     */
    @Getter
    @Setter
    private WorkingMinute toMinute;

    /**
     * 勤務時間（分）
     */
    @Getter
    private WorkingMinute workMinute;

    /**
     * 残業時間（分）
     */
    @Getter
    private WorkingMinute overWorkMinute;

    /**
     * 変換後勤務時間
     */
    @Getter
    private WorkingMinute convertedWorkMinute;

    /**
     * 変換後残業時間
     */
    @Getter
    private WorkingMinute convertedOverWorkMinute;

    /**
     * コンストラクタ
     */
    public CalculateTime(
            WorkTime workTimeFrom,
            WorkTime workTimeTo,
            WorkingHour fromHour,
            WorkingMinute fromMinute,
            WorkingHour toHour,
            WorkingMinute toMinute
    ) {

        this.workTimeFrom = workTimeFrom;
        this.workTimeTo = workTimeTo;
        this.fromHour = fromHour;
        this.fromMinute = fromMinute;
        this.toHour = toHour;
        this.toMinute = toMinute;

        this.calculateWorkMinute();
        this.calculateBreakTime();
        this.calculateOverWorkMinute();
        this.convertWorkMinuteUnit();
        this.convertOverWorkMinuteUnit();
    }

    // サービス残業終了時刻（時）
    private final Integer SERVICE_OVER_WORK_END_HOUR = 8;

    // サービス残業終了時刻（分）
    private final Integer SERVICE_OVER_WORK_END_MINUTE = 59;

    // 勤務開始時刻（時）
    private final BigDecimal WORK_START_HOUR = BigDecimal.valueOf(9);

    // 勤務開始時刻（分）
    private final BigDecimal WORK_START_MINUTE = BigDecimal.valueOf(0);

    // １時間
    private final BigDecimal ONE_HOUR = BigDecimal.valueOf(60);

    // 休憩時間
    private final BigDecimal BREAK_TIME_START_HOUR_1 = BigDecimal.valueOf(12);
    private final BigDecimal BREAK_TIME_END_HOUR_1 = BigDecimal.valueOf(13);
    private final BigDecimal BREAK_TIME_START_HOUR_2 = BigDecimal.valueOf(18);
    private final BigDecimal BREAK_TIME_END_HOUR_2 = BigDecimal.valueOf(19);
    private final BigDecimal BREAK_TIME_START_HOUR_3 = BigDecimal.valueOf(21);
    private final BigDecimal BREAK_TIME_END_HOUR_3 = BigDecimal.valueOf(22);

    // １日あたりの勤務時間
    private final BigDecimal ONE_DAY_WORK_MINUTE = BigDecimal.valueOf(8);

    // 出退勤時間の単位（区切り）
    private final BigDecimal CALC_UNIT_MINUTE = BigDecimal.valueOf(15).setScale(2, BigDecimal.ROUND_HALF_UP);

    /**
     * 勤務時間計算
     */
    private void calculateWorkMinute() {

        // 出勤時刻に09:00以前（00:00〜08:59）を指定された場合、09:00出勤とみなして計算
        // ※退勤時刻も自動的に24:00以降を無視（サービス残業）
        if (LocalTime.of(SERVICE_OVER_WORK_END_HOUR, SERVICE_OVER_WORK_END_MINUTE).compareTo(workTimeFrom.convertLocalTime()) > 0) {
            fromHour = new WorkingHour(WORK_START_HOUR);
            fromMinute = new WorkingMinute(WORK_START_MINUTE);
        }

        // 勤務時間（分）を計算
        // int workMinute = endH * 60 + endM - (startH * 60 + startM)
        workMinute = new WorkingMinute(
                toHour.getValue().multiply(ONE_HOUR)
                        .add(toMinute.getValue())
                        .add((fromHour.getValue().multiply(ONE_HOUR)
                                .add(fromMinute.getValue())).multiply(BigDecimal.valueOf(-1))
                        )
        );
    }

    /**
     * 休憩時間計算
     */
    private void calculateBreakTime() {

        // 休憩時間12:00〜13:00分を減算
        if (toHour.getValue().compareTo(BREAK_TIME_START_HOUR_1) == 0) {
            // 12:01〜退勤時刻までの分を減算
            workMinute = new WorkingMinute(workMinute.getValue().add(toMinute.getValue().multiply(BigDecimal.valueOf(-1))));
        } else if (toHour.getValue().compareTo(BREAK_TIME_END_HOUR_1) >= 0) {
            // 12:00〜13:00分を減算
            workMinute = new WorkingMinute(workMinute.getValue().add(ONE_HOUR.multiply(BigDecimal.valueOf(-1))));
        }

        // 休憩時間18:00〜19:00分を減算
        if (toHour.getValue().compareTo(BREAK_TIME_START_HOUR_2) == 0) {
            // 18:01〜退勤時刻までの分を減算
            workMinute = new WorkingMinute(workMinute.getValue().add(toMinute.getValue().multiply(BigDecimal.valueOf(-1))));
        } else if (toHour.getValue().compareTo(BREAK_TIME_END_HOUR_2) >= 0) {
            // 18:00〜19:00分を減算
            workMinute = new WorkingMinute(workMinute.getValue().add(ONE_HOUR.multiply(BigDecimal.valueOf(-1))));
        }

        // 休憩時間21:00〜22:00分を減算
        if (toHour.getValue().compareTo(BREAK_TIME_START_HOUR_3) == 0) {
            // 21:01〜退勤時刻までの分を減算
            workMinute = new WorkingMinute(workMinute.getValue().add(toMinute.getValue().multiply(BigDecimal.valueOf(-1))));
        } else if (toHour.getValue().compareTo(BREAK_TIME_END_HOUR_3) >= 0) {
            // 21:00〜22:00分を減算
            workMinute = new WorkingMinute(workMinute.getValue().add(ONE_HOUR.multiply(BigDecimal.valueOf(-1))));
        }
    }

    /**
     * 残業時間計算
     */
    private void calculateOverWorkMinute() {

        // 残業時間（分）を計算
        //int overWorkMinute = Math.max(workMinute - 8 * 60, 0);
        overWorkMinute = new WorkingMinute(
                BigDecimal.valueOf(
                        Math.max(Integer.valueOf(workMinute.getValue().toString())
                                - Integer.valueOf(ONE_DAY_WORK_MINUTE.toString())
                                * Integer.valueOf(ONE_HOUR.toString()), 0
                        )
                )
        );
    }

    /**
     * 勤務時間を15分単位に変換
     */
    private void convertWorkMinuteUnit() {

        // 60 / 15 = 0.25
        BigDecimal calcUnit = CALC_UNIT_MINUTE.divide(ONE_HOUR, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);

        // 勤務時間（分）および残業時間（分）を60で割った端数を15分単位に変換
        // （1〜14分→0.00、15〜29分→0.25、30〜44分→0.50、45〜59分→0.75、60〜74分→1.00、・・・）
        BigDecimal workMinuteDecimalVal =
                BigDecimal.valueOf(
                        Double.valueOf(
                                (workMinute.getValue().divide(ONE_HOUR, 2, BigDecimal.ROUND_HALF_UP)).toString()
                        ) % 1
                ).setScale(2, BigDecimal.ROUND_HALF_UP);

        convertedWorkMinute = new WorkingMinute(
                workMinute.getValue().divide(ONE_HOUR, 2, BigDecimal.ROUND_HALF_UP)
                        .add(BigDecimal.valueOf(-1).multiply(workMinuteDecimalVal))
                        .add(convertMinuteToUnit(calcUnit, workMinuteDecimalVal))
        );
    }

    /**
     * 残業時間を15分単位に変換
     */
    private void convertOverWorkMinuteUnit() {

        // 60 / 15 = 0.25
        BigDecimal calcUnit = CALC_UNIT_MINUTE.divide(ONE_HOUR, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);

        // 勤務時間（分）および残業時間（分）を60で割った端数を15分単位に変換
        // （1〜14分→0.00、15〜29分→0.25、30〜44分→0.50、45〜59分→0.75、60〜74分→1.00、・・・）
        BigDecimal overWorkMinuteDecimalVal =
                BigDecimal.valueOf(
                        Double.valueOf(
                                (overWorkMinute.getValue().divide(ONE_HOUR, 2, BigDecimal.ROUND_HALF_UP)).toString()
                        ) % 1
                ).setScale(2, BigDecimal.ROUND_HALF_UP);

        convertedOverWorkMinute = new WorkingMinute(
                overWorkMinute.getValue().divide(ONE_HOUR, 2, BigDecimal.ROUND_HALF_UP)
                        .add(BigDecimal.valueOf(-1).multiply(overWorkMinuteDecimalVal))
                        .add(convertMinuteToUnit(calcUnit, overWorkMinuteDecimalVal)
                        )
        );
    }

    /**
     * 単位変換
     *
     * @param calcUnit  　変換する単位
     * @param targetVal 　対象の時間
     * @return 変換された時間
     */
    private BigDecimal convertMinuteToUnit(BigDecimal calcUnit, BigDecimal targetVal) {

        // 60分（1h）未満の時間を15分（0.25h）単位に変換する
        for (int n = 0; n < 4; n++) {
            if (targetVal.compareTo(calcUnit.multiply(BigDecimal.valueOf(n))) >= 0 &&
                    targetVal.compareTo(calcUnit.multiply(BigDecimal.valueOf(n + 1))) < 0) {
                return calcUnit.multiply(BigDecimal.valueOf(n));
            }
        }
        return BigDecimal.ZERO;
    }

    public WorkingData makeWorkingData(WorkDate workDate) {
        return new WorkingData(
                workDate,
                this.getWorkTimeFrom(),
                this.getWorkTimeTo(),
                this.getConvertedWorkMinute(),
                this.getConvertedOverWorkMinute()
        );
    }
}
