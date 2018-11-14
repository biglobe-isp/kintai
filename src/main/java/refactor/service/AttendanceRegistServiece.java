package refactor.service;

import refactor.domain.repository.AttendanceRepositoryInsert;
//TODO ここにDOMAINはNGか
import refactor.domain.dto.RegistAttendanceEvent;
import refactor.domain.AttendanceRegistDomain;

public class AttendanceRegistServiece {
    private AttendanceRegistDomain aDomain = new AttendanceRegistDomain();


    public void inputAttendance(RegistAttendanceEvent data, AttendanceRepositoryInsert repository){

        aDomain.inputAttendance(data,repository);
    }

}
