package refoctor.domain.japan;

public interface DayWorkMinutesRepository {
    void dayInPut(DateDomain dateDomain, StartTime startTime, EndTime endTime, WorkTime workTime);
}