package refactor.service;

import refactor.api.repository.AttendanceRepositoryInsert;
import refactor.api.repository.AttendanceRepositorySelect;
//TODO ここにDOMAINはNGか
import refactor.domain.dto.RegistAttendanceEvent;
import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.OutPutTotalData;
import refactor.domain.AttendanceDomain;

public class AttendanceServiece {
    private AttendanceDomain aDomain = new AttendanceDomain();


    public void inputAttendance(RegistAttendanceEvent data, AttendanceRepositoryInsert repository){

        aDomain.inputAttendance(data,repository);
    }


    public OutPutTotalData totalAttendance(DisplayAttendanceEvent data, AttendanceRepositorySelect repository){
        OutPutTotalData outputData = aDomain.totalAttendance(data,repository);
        return outputData;
    }
}
