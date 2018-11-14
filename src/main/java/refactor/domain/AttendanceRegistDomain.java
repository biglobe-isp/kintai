package refactor.domain;

import refactor.domain.dto.Item.WorkAndOverWorkMinutesItem;
import refactor.domain.repository.AttendanceRepositoryInsert;
import refactor.domain.dto.RegistAttendanceEvent;
import refactor.domain.dto.AttendanceData;

public class AttendanceRegistDomain {
    public void inputAttendance(RegistAttendanceEvent data, AttendanceRepositoryInsert repository){

        WorkAndOverWorkMinutesItem workAndOverWorkMinutesItem =new WorkAndOverWorkMinutesItem(data.calculateWorkTime(),data.caluculateOverWorkTime());

        AttendanceData insertData = new AttendanceData(
        data.getDate(),
        data.getStartTime(),
        data.getEndTime(),
        workAndOverWorkMinutesItem,
        data.getNowTime()
        );

        repository.insert(insertData);
    }


}
