package refactor.domain;

import refactor.api.form.InputData;
import refactor.datasource.AttendanceRepository;
import refactor.datasource.entity.AttendanceData;
import refactor.domain.dto.TimeData;
import refactor.domain.util.TimeManager;

public class AttendanceDomain {
    public void inputAttendance(InputData data){
        TimeManager manager = new TimeManager();
        TimeData startTime = manager.sptlitHourMinutes(data.getStartTime());
        TimeData endTime = manager.sptlitHourMinutes(data.getEndTime());
        int workMinutes = manager.calcWorkMinutes(startTime,endTime);
        int overWorkMinutes = manager.calcOverWorkMinutes(workMinutes);

        AttendanceData insertData = new AttendanceData();
        insertData.setDate(data.getDate());
        insertData.setStartTime(data.getStartTime());
        insertData.setEndTime(data.getEndTime());
        insertData.setWorkMinutes(workMinutes);
        insertData.setOverWorkMinutes(overWorkMinutes);
        insertData.setNowTime(data.getNowTime());

        AttendanceRepository repository = new AttendanceRepository();

        repository.insert(insertData);
    }

    public void totalAttendance(InputData data){

    }
}
