package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeInterval;
import com.naosim.dddwork.kintai.service.RegulationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AttendanceTimeIntervalDomainService {
    private final RegulationRepository regulationRepository;

    public AttendanceTimeInterval acceptOrFire(
            @NonNull AttendanceDate attendanceDate, @NonNull TimeInterval interval) throws Exception {
        RegulatedWorkingTimeInterval regulatedWorkingTimeInterval = regulationRepository.fetchRegulatedWorkingTimeInterval(
                attendanceDate);

        if (!regulatedWorkingTimeInterval.hasFollowedRegulatedStartTime(interval)) {
            throw new IllegalStateException("あなたは遅刻しましたので、クビです。");
        }
        return new AttendanceTimeInterval(interval);
    }
}
