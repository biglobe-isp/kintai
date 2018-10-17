package refoctor.domain;

public interface DayWorkMinutesRepository {
    void dayOutPut(String date, String start, String end, int workMinutes, int overWorkMinutes, String now);
}