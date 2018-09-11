//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.naosim.dddwork.domain.duty;

import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkTime;
import com.naosim.dddwork.domain.word.WorkingHour;
import com.naosim.dddwork.domain.word.WorkingMinute;
import java.math.BigDecimal;
import java.time.LocalTime;

public class CalculateTime {
    private WorkTime workTimeFrom;
    private WorkTime workTimeTo;
    private WorkingHour fromHour;
    private WorkingMinute fromMinute;
    private WorkingHour toHour;
    private WorkingMinute toMinute;
    private WorkingMinute workMinute;
    private WorkingMinute overWorkMinute;
    private WorkingMinute convertedWorkMinute;
    private WorkingMinute convertedOverWorkMinute;
    private final Integer SERVICE_OVER_WORK_END_HOUR = 8;
    private final Integer SERVICE_OVER_WORK_END_MINUTE = 59;
    private final BigDecimal WORK_START_HOUR = BigDecimal.valueOf(9L);
    private final BigDecimal WORK_START_MINUTE = BigDecimal.valueOf(0L);
    private final BigDecimal ONE_HOUR = BigDecimal.valueOf(60L);
    private final BigDecimal BREAK_TIME_START_HOUR_1 = BigDecimal.valueOf(12L);
    private final BigDecimal BREAK_TIME_END_HOUR_1 = BigDecimal.valueOf(13L);
    private final BigDecimal BREAK_TIME_START_HOUR_2 = BigDecimal.valueOf(18L);
    private final BigDecimal BREAK_TIME_END_HOUR_2 = BigDecimal.valueOf(19L);
    private final BigDecimal BREAK_TIME_START_HOUR_3 = BigDecimal.valueOf(21L);
    private final BigDecimal BREAK_TIME_END_HOUR_3 = BigDecimal.valueOf(22L);
    private final BigDecimal ONE_DAY_WORK_MINUTE = BigDecimal.valueOf(8L);
    private final BigDecimal CALC_UNIT_MINUTE = BigDecimal.valueOf(15L).setScale(2, 4);

    public CalculateTime(WorkTime workTimeFrom, WorkTime workTimeTo, WorkingHour fromHour, WorkingMinute fromMinute, WorkingHour toHour, WorkingMinute toMinute) {
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

    private void calculateWorkMinute() {
        if (LocalTime.of(this.SERVICE_OVER_WORK_END_HOUR, this.SERVICE_OVER_WORK_END_MINUTE).compareTo(this.workTimeFrom.convertLocalTime()) > 0) {
            this.fromHour = new WorkingHour(this.WORK_START_HOUR);
            this.fromMinute = new WorkingMinute(this.WORK_START_MINUTE);
        }

        this.workMinute = new WorkingMinute(this.toHour.getValue().multiply(this.ONE_HOUR).add(this.toMinute.getValue()).add(this.fromHour.getValue().multiply(this.ONE_HOUR).add(this.fromMinute.getValue()).multiply(BigDecimal.valueOf(-1L))));
    }

    private void calculateBreakTime() {
        if (this.toHour.getValue().compareTo(this.BREAK_TIME_START_HOUR_1) == 0) {
            this.workMinute = new WorkingMinute(this.workMinute.getValue().add(this.toMinute.getValue().multiply(BigDecimal.valueOf(-1L))));
        } else if (this.toHour.getValue().compareTo(this.BREAK_TIME_END_HOUR_1) >= 0) {
            this.workMinute = new WorkingMinute(this.workMinute.getValue().add(this.ONE_HOUR.multiply(BigDecimal.valueOf(-1L))));
        }

        if (this.toHour.getValue().compareTo(this.BREAK_TIME_START_HOUR_2) == 0) {
            this.workMinute = new WorkingMinute(this.workMinute.getValue().add(this.toMinute.getValue().multiply(BigDecimal.valueOf(-1L))));
        } else if (this.toHour.getValue().compareTo(this.BREAK_TIME_END_HOUR_2) >= 0) {
            this.workMinute = new WorkingMinute(this.workMinute.getValue().add(this.ONE_HOUR.multiply(BigDecimal.valueOf(-1L))));
        }

        if (this.toHour.getValue().compareTo(this.BREAK_TIME_START_HOUR_3) == 0) {
            this.workMinute = new WorkingMinute(this.workMinute.getValue().add(this.toMinute.getValue().multiply(BigDecimal.valueOf(-1L))));
        } else if (this.toHour.getValue().compareTo(this.BREAK_TIME_END_HOUR_3) >= 0) {
            this.workMinute = new WorkingMinute(this.workMinute.getValue().add(this.ONE_HOUR.multiply(BigDecimal.valueOf(-1L))));
        }

    }

    private void calculateOverWorkMinute() {
        this.overWorkMinute = new WorkingMinute(BigDecimal.valueOf((long)Math.max(Integer.valueOf(this.workMinute.getValue().toString()) - Integer.valueOf(this.ONE_DAY_WORK_MINUTE.toString()) * Integer.valueOf(this.ONE_HOUR.toString()), 0)));
    }

    private void convertWorkMinuteUnit() {
        BigDecimal calcUnit = this.CALC_UNIT_MINUTE.divide(this.ONE_HOUR, 4).setScale(2, 4);
        BigDecimal workMinuteDecimalVal = BigDecimal.valueOf(Double.valueOf(this.workMinute.getValue().divide(this.ONE_HOUR, 2, 4).toString()) % 1.0D).setScale(2, 4);
        this.convertedWorkMinute = new WorkingMinute(this.workMinute.getValue().divide(this.ONE_HOUR, 2, 4).add(BigDecimal.valueOf(-1L).multiply(workMinuteDecimalVal)).add(this.convertMinuteToUnit(calcUnit, workMinuteDecimalVal)));
    }

    private void convertOverWorkMinuteUnit() {
        BigDecimal calcUnit = this.CALC_UNIT_MINUTE.divide(this.ONE_HOUR, 4).setScale(2, 4);
        BigDecimal overWorkMinuteDecimalVal = BigDecimal.valueOf(Double.valueOf(this.overWorkMinute.getValue().divide(this.ONE_HOUR, 2, 4).toString()) % 1.0D).setScale(2, 4);
        this.convertedOverWorkMinute = new WorkingMinute(this.overWorkMinute.getValue().divide(this.ONE_HOUR, 2, 4).add(BigDecimal.valueOf(-1L).multiply(overWorkMinuteDecimalVal)).add(this.convertMinuteToUnit(calcUnit, overWorkMinuteDecimalVal)));
    }

    private BigDecimal convertMinuteToUnit(BigDecimal calcUnit, BigDecimal targetVal) {
        for(int n = 0; n < 4; ++n) {
            if (targetVal.compareTo(calcUnit.multiply(BigDecimal.valueOf((long)n))) >= 0 && targetVal.compareTo(calcUnit.multiply(BigDecimal.valueOf((long)(n + 1)))) < 0) {
                return calcUnit.multiply(BigDecimal.valueOf((long)n));
            }
        }

        return BigDecimal.ZERO;
    }

    public WorkingData makeWorkingData(WorkDate workDate) {
        return new WorkingData(workDate, this.getWorkTimeFrom(), this.getWorkTimeTo(), this.getConvertedWorkMinute(), this.getConvertedOverWorkMinute());
    }

    public String toString() {
        return "CalculateTime(" + this.getWorkTimeFrom() + ", " + this.getWorkTimeTo() + ", " + this.getFromHour() + ", " + this.getFromMinute() + ", " + this.getToHour() + ", " + this.getToMinute() + ", " + this.getWorkMinute() + ", " + this.getOverWorkMinute() + ", " + this.getConvertedWorkMinute() + ", " + this.getConvertedOverWorkMinute() + ", " + this.SERVICE_OVER_WORK_END_HOUR + ", " + this.SERVICE_OVER_WORK_END_MINUTE + ", " + this.WORK_START_HOUR + ", " + this.WORK_START_MINUTE + ", " + this.ONE_HOUR + ", " + this.BREAK_TIME_START_HOUR_1 + ", " + this.BREAK_TIME_END_HOUR_1 + ", " + this.BREAK_TIME_START_HOUR_2 + ", " + this.BREAK_TIME_END_HOUR_2 + ", " + this.BREAK_TIME_START_HOUR_3 + ", " + this.BREAK_TIME_END_HOUR_3 + ", " + this.ONE_DAY_WORK_MINUTE + ", " + this.CALC_UNIT_MINUTE + ")";
    }

    public WorkTime getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    public void setWorkTimeFrom(WorkTime workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
    }

    public WorkTime getWorkTimeTo() {
        return this.workTimeTo;
    }

    public void setWorkTimeTo(WorkTime workTimeTo) {
        this.workTimeTo = workTimeTo;
    }

    public WorkingHour getFromHour() {
        return this.fromHour;
    }

    public void setFromHour(WorkingHour fromHour) {
        this.fromHour = fromHour;
    }

    public WorkingMinute getFromMinute() {
        return this.fromMinute;
    }

    public void setFromMinute(WorkingMinute fromMinute) {
        this.fromMinute = fromMinute;
    }

    public WorkingHour getToHour() {
        return this.toHour;
    }

    public void setToHour(WorkingHour toHour) {
        this.toHour = toHour;
    }

    public WorkingMinute getToMinute() {
        return this.toMinute;
    }

    public void setToMinute(WorkingMinute toMinute) {
        this.toMinute = toMinute;
    }

    public WorkingMinute getWorkMinute() {
        return this.workMinute;
    }

    public WorkingMinute getOverWorkMinute() {
        return this.overWorkMinute;
    }

    public WorkingMinute getConvertedWorkMinute() {
        return this.convertedWorkMinute;
    }

    public WorkingMinute getConvertedOverWorkMinute() {
        return this.convertedOverWorkMinute;
    }
}
