package com.naosim.dddwork.service;

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

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("H:m");

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

    private void validateWorkTime(LocalTime st, LocalTime et, WorkRegulation workRegulation) {
        if (st.isAfter(et)) {
            throw new WorkRegulationException("開始時間は終了時間より小さい値を指定してください");
        }

        LocalTime stMin = workRegulation.getMinStartTime().getValue();
        if (st.isBefore(stMin)) {
            throw new WorkRegulationException("開始時間は" + stMin.format(fmt) + "以降の値を指定してください");
        }

        LocalTime stMax = workRegulation.getMaxStartTime().getValue();
        if (st.isAfter(stMax)) {
            throw new WorkRegulationException("開始時間は" + stMin.format(fmt) + "以前の値を指定してください");
        }

        LocalTime etMin = workRegulation.getMinEndTime().getValue();
        if (st.isBefore(etMin)) {
            throw new WorkRegulationException("終了時間は" + etMin.format(fmt) + "以降の値を指定してください");
        }

        LocalTime etMax = workRegulation.getMaxEndTime().getValue();
        if (st.isAfter(etMax)) {
            throw new WorkRegulationException("終了時間は" + etMax.format(fmt) + "以前の値を指定してください");
        }
    }

    private int calculateStayTime(LocalTime st, LocalTime et) {
        return (int) ChronoUnit.MINUTES.between(st, et);
    }

    private int calculateRestTimes(LocalTime st, LocalTime et, List<TimePointPair> restTimes) {
        return restTimes.stream()
                .map((rt -> calculateRestTime(st, et, rt.getStartTime().getValue(), rt.getEndTime().getValue())))
                .reduce(Integer::sum).orElse(0);
    }

    private int calculateRestTime(LocalTime st, LocalTime et, LocalTime rSt, LocalTime rEt) {
        if (st.isBefore(rEt) || et.isAfter(rSt)) {
            return 0;
        }

        if (st.isAfter(rSt)) {
            return (int) ChronoUnit.MINUTES.between(st, rEt);
        }

        if (et.isBefore(rEt)) {
            return (int) ChronoUnit.MINUTES.between(rSt, et);
        }

        return (int) ChronoUnit.MINUTES.between(rSt, rEt);
    }

    private WorkTimeOfDay createWorkTimeOfDay(int stayMinute, int restMinute, int standardMinute) {
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

    private WorkTimeOfMonth createWorkTimeOfMonth(int totalWorkMinuteValue, int totalOverWorkMinuteValue) {
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
