package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeInterval;
import com.naosim.dddwork.kintai.service.RegulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AttendanceTimeIntervalFactory {
    private final RegulationRepository regulationRepository;

    //    public AttendanceTimeInterval create(
//            AttendanceDate attendanceDate,
//            StartTime startTime,
//            EndTime endTime) throws Exception {
//        if (startTime == null || endTime == null) {
//            throw new IllegalArgumentException("開始時間または終了時間がありません。");
//        }
//        RegulatedWorkingTimeInterval regulatedWorkingTimeInterval = regulationRepository.fetchRegulatedWorkingTimeInterval(
//                attendanceDate);
//
//        if (!regulatedWorkingTimeInterval.hasFollowedRegulatedStartTime(startTime)) {
//            throw new IllegalStateException("あなたは遅刻しましたので、クビです。");
//        }
//        return new AttendanceTimeInterval(new TimeInterval(startTime, endTime));
//    }
    public AttendanceTimeInterval create(
            AttendanceDate attendanceDate, TimeInterval interval) throws Exception {
        if (interval == null) {
            throw new IllegalArgumentException("開始時間または終了時間がありません。");
        }
        RegulatedWorkingTimeInterval regulatedWorkingTimeInterval = regulationRepository.fetchRegulatedWorkingTimeInterval(
                attendanceDate);

        if (!regulatedWorkingTimeInterval.hasFollowedRegulatedStartTime(interval)) {
            throw new IllegalStateException("あなたは遅刻しましたので、クビです。");
        }
        return new AttendanceTimeInterval(interval);
    }
}
