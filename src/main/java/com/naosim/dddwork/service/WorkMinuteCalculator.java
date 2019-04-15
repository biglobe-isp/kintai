package com.naosim.dddwork.service;

import com.google.common.annotations.VisibleForTesting;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimePointPair;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulation;
import com.naosim.dddwork.domain.WorkRegulationException;
import com.naosim.dddwork.domain.WorkRegulationRepository;
import com.naosim.dddwork.domain.WorkTimeOfDay;
import com.naosim.dddwork.domain.WorkTimeOfMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
class WorkMinuteCalculator {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    private final WorkRegulationRepository workRegulationRepository;

    @Autowired
    public WorkMinuteCalculator(WorkRegulationRepository workRegulationRepository) {
        this.workRegulationRepository = workRegulationRepository;
    }

    WorkTimeOfDay calculateOfDay(TimePoint startTime, TimePoint endTime) {
        LocalTime st = startTime.getValue();
        LocalTime et = endTime.getValue();
        WorkRegulation workRegulation = workRegulationRepository.fetchDefault();

        validateWorkTime(st, et, workRegulation);

        int stayMinute = calculateStayTime(st, et);
        int restMinute = calculateRestTimes(st, et, workRegulation.getRestTimes());

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
    void validateWorkTime(LocalTime startTime, LocalTime endTime, WorkRegulation workRegulation) {
        if (startTime.isAfter(endTime)) {
            throw new WorkRegulationException("開始時間は終了時間より小さい値を指定してください");
        }

        LocalTime minStartTime = workRegulation.getMinStartTime().getValue();
        if (startTime.isBefore(minStartTime)) {
            throw new WorkRegulationException("開始時間は" + minStartTime.format(TIME_FORMATTER) + "以降の値を指定してください");
        }

        LocalTime maxStartTime = workRegulation.getMaxStartTime().getValue();
        if (startTime.isAfter(maxStartTime)) {
            throw new WorkRegulationException("開始時間は" + maxStartTime.format(TIME_FORMATTER) + "までの値を指定してください");
        }

        LocalTime minEndTime = workRegulation.getMinEndTime().getValue();
        if (startTime.isBefore(minEndTime)) {
            throw new WorkRegulationException("終了時間は" + minEndTime.format(TIME_FORMATTER) + "以降の値を指定してください");
        }

        LocalTime maxEndTime = workRegulation.getMaxEndTime().getValue();
        if (startTime.isAfter(maxEndTime)) {
            throw new WorkRegulationException("終了時間は" + maxEndTime.format(TIME_FORMATTER) + "までの値を指定してください");
        }
    }

    @VisibleForTesting
    int calculateStayTime(LocalTime startTime, LocalTime endTime) {
        return (int) ChronoUnit.MINUTES.between(startTime, endTime);
    }

    @VisibleForTesting
    int calculateRestTimes(LocalTime startTime, LocalTime endTime, List<TimePointPair> restTimes) {
        return restTimes.stream()
                .map((rt -> calculateRestTime(
                        startTime,
                        endTime,
                        rt.getStartTime().getValue(),
                        rt.getEndTime().getValue()
                )))
                .reduce(Integer::sum).orElse(0);
    }

    @VisibleForTesting
    int calculateRestTime(LocalTime startTime, LocalTime endTime, LocalTime restStartTime, LocalTime restEndTime) {
        if (startTime.isAfter(restEndTime) || endTime.isBefore(restStartTime)) {
            return 0;
        }

        if (startTime.isAfter(restStartTime)) {
            return (int) ChronoUnit.MINUTES.between(startTime, restEndTime);
        }

        if (endTime.isBefore(restEndTime)) {
            return (int) ChronoUnit.MINUTES.between(restStartTime, endTime);
        }

        return (int) ChronoUnit.MINUTES.between(restStartTime, restEndTime);
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
