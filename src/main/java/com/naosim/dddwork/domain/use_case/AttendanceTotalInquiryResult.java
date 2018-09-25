package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.attendance.AttendanceDetail;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@EqualsAndHashCode
public class AttendanceTotalInquiryResult {

    @Getter
    private final Optional<List<AttendanceDetail>> attendanceDetail;
}
