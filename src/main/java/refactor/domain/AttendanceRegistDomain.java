package refactor.domain;

import refactor.api.repository.AttendanceRepositoryInsert;
import refactor.api.repository.AttendanceRepositorySelect;
import refactor.domain.dto.RegistAttendanceEvent;
import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.OutPutTotalData;
import refactor.domain.dto.AttendanceData;
import refactor.domain.dto.TimeData;
import refactor.domain.util.TimeManager;

import java.util.Set;

public class AttendanceDomain {
    public void inputAttendance(RegistAttendanceEvent data, AttendanceRepositoryInsert repository){

        //時間の演算
        TimeManager manager = new TimeManager();
        TimeData startTime = manager.sptlitHourMinutes(data.getStartTime());
        TimeData endTime = manager.sptlitHourMinutes(data.getEndTime());

        //勤務時間の算出
        int workMinutes = manager.calcWorkMinutes(startTime,endTime);
        //残業時間の算出
        int overWorkMinutes = manager.calcOverWorkMinutes(workMinutes);

        AttendanceData insertData = new AttendanceData(
        data.getDate(),
        data.getStartTime(),
        data.getEndTime(),
        workMinutes,
        overWorkMinutes,
        data.getNowTime());

        repository.insert(insertData);
    }

    public OutPutTotalData totalAttendance(DisplayAttendanceEvent data,AttendanceRepositorySelect repository){
        AttendanceData.WorkMinutesData mData = repository.select(data);
        Set<String> keySet = mData.getTotalWorkMinutes().keySet();


        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        for(String key : keySet) {
            totalWorkMinutes += ((Integer)mData.getTotalWorkMinutes().get(key)).intValue();
            totalOverWorkMinutes += ((Integer) mData.getTotalOverWorkMinutes().get(key)).intValue();
        }

        return new OutPutTotalData(totalWorkMinutes,totalOverWorkMinutes);
    }
}
