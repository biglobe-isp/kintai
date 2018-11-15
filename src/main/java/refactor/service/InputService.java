package refactor.service;


import refactor.domain.CsvWriterRepo;
import refactor.domain.EndTime;
import refactor.domain.OverWorkMinutes;
import refactor.domain.StartTime;
import refactor.domain.WorkMinutes;

import java.time.LocalDateTime;

public class InputService {
    public void inputWorkTimeData(String[] args, CsvWriterRepo csvWriterRepo) {
        //String yearMonth = args[1];

        String date = args[1];
        String start = args[2];
        String end = args[3];
        String now = LocalDateTime.now().toString();


        //この辺をApi層で綺麗にする(Api層でnewする)
        //newを引数で渡すようにする
        //new staticを呼ぶのは依存が強い
        StartTime startTime = new StartTime(start);
        EndTime endTime = new EndTime(end);
        int workMinutes = new WorkMinutes().calculateWorkMinutes(startTime, endTime);
        int overWorkMinutes = new OverWorkMinutes().calculateOverWorkMinutes(workMinutes);
        csvWriterRepo.registerWorkingTime(date, start, end, workMinutes, overWorkMinutes, now);
    }
}
