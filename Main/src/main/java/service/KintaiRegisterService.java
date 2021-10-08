package service;

import domain.KintaiRegisterRepositoryFile;
import domain.OverWorkTime;
import domain.WorkDay;
import domain.WorkEndTime;
import domain.WorkStartTime;
import domain.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KintaiRegisterService {
    @Autowired
    KintaiRegisterRepositoryFile kintaiRegisterRepositoryFile;
    @Autowired
    WorkTime workTime;
    @Autowired
    OverWorkTime overWorkTime;

    public void kintaiRegisterService(WorkDay workDay, WorkStartTime workStartTime, WorkEndTime workEndTime) {
        kintaiRegisterRepositoryFile.regist(
                workDay,
                workStartTime,
                workEndTime,
                workTime.calculate(workStartTime, workEndTime),
                overWorkTime.calculate(workStartTime, workEndTime)
        );
    }
}
