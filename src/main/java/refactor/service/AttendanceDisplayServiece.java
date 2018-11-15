package refactor.service;

import refactor.domain.repository.AttendanceRepositorySelect;
import refactor.domain.AttendanceDisplayDomain;
import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.OutPutTotalData;

public class AttendanceDisplayServiece {
    private AttendanceRepositorySelect repository;

    public AttendanceDisplayServiece(AttendanceRepositorySelect repository) {
        this.repository = repository;
    }

    public OutPutTotalData totalAttendance(DisplayAttendanceEvent data){
        AttendanceDisplayDomain aDomain = new AttendanceDisplayDomain(repository);
        OutPutTotalData outputData = aDomain.totalAttendance(data);
        return outputData;
    }
}
