package refactor.domain;

import refactor.api.form.InputData;
import refactor.api.form.InputTotalData;
import refactor.api.form.OutPutTotalData;
import refactor.datasource.AttendanceRepository;
import refactor.datasource.entity.AttendanceData;
import refactor.datasource.entity.WorkMinutesData;
import refactor.domain.dto.TimeData;
import refactor.domain.util.TimeManager;

import java.util.Set;

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

    public OutPutTotalData totalAttendance(InputTotalData data){
        AttendanceRepository repository = new AttendanceRepository();
        WorkMinutesData mData = repository.select(data);
        Set<String> keySet = mData.getTotalWorkMinutes().keySet();


        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        for(String key : keySet) {
            totalWorkMinutes += ((Integer)mData.getTotalWorkMinutes().get(key)).intValue();
            totalOverWorkMinutes += ((Integer) mData.getTotalOverWorkMinutes().get(key)).intValue();
        }

        OutPutTotalData outputData = new OutPutTotalData();

        outputData.setTotalWorkMinutes(totalWorkMinutes);
        outputData.setTotalOverWorkMinutes(totalOverWorkMinutes);

        return outputData;
    }
}
