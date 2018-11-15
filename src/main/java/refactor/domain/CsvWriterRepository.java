package refactor.domain;

public interface CsvWriterRepository {
    void registerWorkingTime(
            Date date,
            StartTime startTime,
            EndTime endTime,
            int workMinutes,
            int overWorkMinutes,
            NowTime nowTime);
}
