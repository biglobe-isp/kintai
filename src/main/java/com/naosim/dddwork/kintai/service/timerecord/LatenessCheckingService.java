package com.naosim.dddwork.kintai.service.timerecord;

import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeInterval;
import com.naosim.dddwork.kintai.service.RegulationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LatenessCheckingService {
    private final RegulationRepository regulationRepository;

    public AttendanceTimeInterval acceptOrFire(
            @NonNull AttendanceDate attendanceDate, @NonNull TimeInterval interval) throws Exception {
        RegulatedWorkingTimeInterval regulatedWorkingTimeInterval = regulationRepository.fetchRegulatedWorkingTimeInterval(
                attendanceDate);

        if (!regulatedWorkingTimeInterval.hasFollowedRegulatedStartTime(interval)) {
            // 遅刻を表したドメインオブジェクトを返した方が良い。遅刻は例外ではなく、通常あり得る
            // 仕様が間違っていると指摘するのが正解です。素直に実装するのは罠です
            throw new IllegalStateException("あなたは遅刻しましたので、クビです。");
        }
        return new AttendanceTimeInterval(interval);
    }
}
