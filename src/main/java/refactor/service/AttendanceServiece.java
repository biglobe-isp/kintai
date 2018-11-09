package refactor.service;

import refactor.api.form.InputData;
import refactor.domain.AttendanceDomain;

public class AttendanceServiece {
    private AttendanceDomain aDomain = new AttendanceDomain();

    public AttendanceDomain getaDomain() {
        return aDomain;
    }

    public void setaDomain(AttendanceDomain aDomain) {
        this.aDomain = aDomain;
    }


    public void inputAttendance(InputData data){
        aDomain.inputAttendance(data);
    }


    public void totalAttendance(InputData data){
        aDomain.totalAttendance(data);
    }
}
