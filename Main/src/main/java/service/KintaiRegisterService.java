package service;

import datasource.KintaiRegisterRepositoryFileImpl;
import domain.KintaiRegisterRepositoryFile;
import domain.OverWorkTime;
import domain.WorkDay;
import domain.WorkEndTime;
import domain.WorkStartTime;
import domain.WorkTime;

public class KintaiRegisterService {

    public void kintaiRegisterService(WorkDay workDay, WorkStartTime workStartTime, WorkEndTime workEndTime) {
        KintaiRegisterRepositoryFile kintaiRegisterRepositoryFile = new KintaiRegisterRepositoryFileImpl();
        WorkTime workTime = new WorkTime(0);
        OverWorkTime overWorkTime = new OverWorkTime(0);

        kintaiRegisterRepositoryFile.regist(
                workDay,
                workStartTime,
                workEndTime,
                workTime.calculate(workStartTime, workEndTime),
                overWorkTime.calculate(workTime)
        );
    }
}
