package refactor.service;

import refactor.domain.repository.AttendanceRepositoryInsert;
//TODO ここにDOMAINはNGか
import refactor.domain.dto.RegistAttendanceEvent;
import refactor.domain.AttendanceRegistDomain;

public class AttendanceRegistServiece {

    private AttendanceRepositoryInsert repository;

    public AttendanceRegistServiece(AttendanceRepositoryInsert repository) {
        this.repository = repository;
    }

    public void inputAttendance(RegistAttendanceEvent data){
        AttendanceRegistDomain aDomain = new AttendanceRegistDomain(repository);
        aDomain.inputAttendance(data);
    }

}
