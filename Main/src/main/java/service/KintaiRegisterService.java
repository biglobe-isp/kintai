package service;

import domain.KintaiRegisterRepositoryFile;
import domain.OverWorkTime;
import domain.WorkDay;
import domain.WorkEndTime;
import domain.WorkStartTime;
import domain.WorkTime;

public class KintaiRegisterService {
    //フィールドにRepo持たせてほしい
    //serviceにdatasourceがあらわれてるのはおかしい（import)
    //calを使う場合どうやったら0を渡さずに作るか。もしできなさそうであればクラス図を変えて良い。ただクラスを増やしたり構成を変えたりせずに

    private KintaiRegisterRepositoryFile kintaiRegisterRepositoryFile;

    public void kintaiRegisterService(WorkDay workDay, WorkStartTime workStartTime, WorkEndTime workEndTime) {
        WorkTime workTime = new WorkTime(0);
        OverWorkTime overWorkTime = new OverWorkTime(0);

        kintaiRegisterRepositoryFile.register(
                workDay,
                workStartTime,
                workEndTime,
                workTime.calculate(workStartTime, workEndTime),
                overWorkTime.calculate(workTime)
        );
    }
}
