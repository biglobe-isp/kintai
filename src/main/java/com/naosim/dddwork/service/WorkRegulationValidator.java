package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkRegulation;
import org.springframework.stereotype.Component;

@Component
class WorkRegulationValidator {

    String validateWorkTime(TimePoint startTime, TimePoint endTime, WorkRegulation workRegulation) {
        if (startTime.isAfter(endTime)) {
            return "開始時間は終了時間より小さい値を指定してください";
        }

        if (startTime.isBefore(workRegulation.getMinStartTime())) {
            return "開始時間が早すぎます";
        }

        if (startTime.isAfter(workRegulation.getMaxStartTime())) {
            return "開始時間が遅すぎます";
        }

        if (endTime.isBefore(workRegulation.getMinEndTime())) {
            return "終了時間が早すぎます";
        }

        if (endTime.isAfter(workRegulation.getMaxEndTime())) {
            return "終了時間が遅すぎます";
        }

        return "";
    }
}
