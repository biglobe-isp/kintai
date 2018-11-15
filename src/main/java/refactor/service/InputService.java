package refactor.service;


import refactor.domain.CsvWriterRepository;
import refactor.domain.Date;
import refactor.domain.EndTime;
import refactor.domain.NowTime;
import refactor.domain.OverWorkMinutes;
import refactor.domain.StartTime;
import refactor.domain.WorkMinutes;


public class InputService {
    public void inputWorkTimeData(
            Date date,
            StartTime startTime,
            EndTime endTime,
            CsvWriterRepository csvWriterRepository, NowTime nowTime) {

        int workMinutes = new WorkMinutes().calculateWorkMinutes(startTime, endTime);
        int overWorkMinutes = new OverWorkMinutes().calculateOverWorkMinutes(workMinutes);
        csvWriterRepository.registerWorkingTime(date, startTime, endTime, workMinutes, overWorkMinutes, nowTime);
    }
}
