package com.naosim.dddwork.service;

import com.google.common.annotations.VisibleForTesting;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulation;
import com.naosim.dddwork.domain.WorkTimeOfDay;
import com.naosim.dddwork.domain.WorkTimeOfMonth;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class WorkMinuteCalculator {

    WorkTimeOfDay calculateOfDay(TimePoint startTime, TimePoint endTime, WorkRegulation workRegulation) {

        int stayMinute = calculateStayTime(startTime, endTime);
        int restMinute = calculateRestTimes(startTime, endTime, workRegulation.getRestTimes());

        return createWorkTimeOfDay(stayMinute, restMinute, workRegulation.getStandardWorkMinute().getValue());
    }

    WorkTimeOfMonth calculateOfMonth(List<WorkTimeOfDay> workTimeOfDays) {
        int totalWorkMinuteValue =
                workTimeOfDays.stream()
                        .map(WorkTimeOfDay::getWorkMinute)
                        .map(WorkMinute::getValue)
                        .reduce(Integer::sum).orElse(0);

        int totalOverWorkMinuteValue =
                workTimeOfDays.stream()
                        .map(WorkTimeOfDay::getOverWorkMinute)
                        .map(WorkMinute::getValue)
                        .reduce(Integer::sum).orElse(0);

        return createWorkTimeOfMonth(totalWorkMinuteValue, totalOverWorkMinuteValue);
    }

    @VisibleForTesting
    int calculateStayTime(TimePoint startTime, TimePoint endTime) {
        return endTime.toMinuteValue() - startTime.toMinuteValue();
    }

    @VisibleForTesting
    int calculateRestTimes(TimePoint startTime, TimePoint endTime, List<TimeRange> restTimes) {
        return restTimes.stream()
                .map((rt -> calculateRestTime(
                        startTime,
                        endTime,
                        rt.getStartTime(),
                        rt.getEndTime()
                )))
                .reduce(Integer::sum).orElse(0);
    }

    @VisibleForTesting
    int calculateRestTime(TimePoint startTime, TimePoint endTime, TimePoint restStartTime, TimePoint restEndTime) {
        if (startTime.isAfter(restEndTime) || endTime.isBefore(restStartTime)) {
            return 0;
        }

        if (startTime.isAfter(restStartTime)) {
            return restEndTime.toMinuteValue() - startTime.toMinuteValue();
        }

        if (endTime.isBefore(restEndTime)) {
            return endTime.toMinuteValue() - restStartTime.toMinuteValue();
        }

        return restEndTime.toMinuteValue() - restStartTime.toMinuteValue();
    }

    @VisibleForTesting
    WorkTimeOfDay createWorkTimeOfDay(int stayMinute, int restMinute, int standardMinute) {
        int allWorkMinuteValue = stayMinute - restMinute;
        boolean isOverWork = allWorkMinuteValue > standardMinute;
        int workMinuteValue = isOverWork ? standardMinute : allWorkMinuteValue;
        int overWorkMinuteValue = isOverWork ? (allWorkMinuteValue - standardMinute) : 0;
        WorkMinute workMinute = new WorkMinute(workMinuteValue);
        WorkMinute overWorkMinute = new WorkMinute(overWorkMinuteValue);

        return new WorkTimeOfDay() {

            @Override
            public WorkMinute getWorkMinute() {
                return workMinute;
            }

            @Override
            public WorkMinute getOverWorkMinute() {
                return overWorkMinute;
            }
        };
    }

    @VisibleForTesting
    WorkTimeOfMonth createWorkTimeOfMonth(int totalWorkMinuteValue, int totalOverWorkMinuteValue) {
        WorkMinute totalWorkMinute = new WorkMinute(totalWorkMinuteValue);
        WorkMinute totalOverWorkMinute = new WorkMinute(totalOverWorkMinuteValue);

        return new WorkTimeOfMonth() {

            @Override
            public WorkMinute getTotalWorkMinute() {
                return totalWorkMinute;
            }

            @Override
            public WorkMinute getTotalOverWorkMinute() {
                return totalOverWorkMinute;
            }
        };
    }
}
