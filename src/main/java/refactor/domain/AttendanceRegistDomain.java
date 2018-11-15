package refactor.domain;

import refactor.domain.dto.Item.WorkAndOverWorkMinutesItem;
import refactor.domain.repository.AttendanceRepositoryInsert;
import refactor.domain.dto.RegistAttendanceEvent;
import refactor.domain.dto.AttendanceData;

public class AttendanceRegistDomain {

    private AttendanceRepositoryInsert repository;

    public AttendanceRegistDomain(AttendanceRepositoryInsert repository) {
        this.repository = repository;
    }

    public void inputAttendance(RegistAttendanceEvent data){

        WorkAndOverWorkMinutesItem workAndOverWorkMinutesItem =new WorkAndOverWorkMinutesItem(data.calculateWorkTime(),data.caluculateOverWorkTime());

        AttendanceData insertData = new AttendanceData(
        data.getDate(),
        data.getStartTime(),
        data.getEndTime(),
        workAndOverWorkMinutesItem,
        data.getNowTime().getNowTime()
        );

        repository.insert(insertData);
    }


}
