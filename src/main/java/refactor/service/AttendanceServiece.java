package refactor.service;

import refactor.api.form.InputData;
import refactor.api.form.InputTotalData;
import refactor.api.form.OutPutTotalData;
import refactor.domain.AttendanceDomain;

public class AttendanceServiece {
    private AttendanceDomain aDomain = new AttendanceDomain();


    public void inputAttendance(InputData data){

        aDomain.inputAttendance(data);
    }


    public OutPutTotalData totalAttendance(InputTotalData data){
        OutPutTotalData outputData = aDomain.totalAttendance(data);
        return outputData;
    }
}
