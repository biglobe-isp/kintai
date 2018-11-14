package refactor.service;

import refactor.domain.repository.AttendanceRepositorySelect;
import refactor.domain.AttendanceDisplayDomain;
import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.OutPutTotalData;

public class AttendanceDisplayServiece {
    private AttendanceDisplayDomain aDomain = new AttendanceDisplayDomain();

    public OutPutTotalData totalAttendance(DisplayAttendanceEvent data, AttendanceRepositorySelect repository){
        OutPutTotalData outputData = aDomain.totalAttendance(data,repository);
        return outputData;
    }
}
