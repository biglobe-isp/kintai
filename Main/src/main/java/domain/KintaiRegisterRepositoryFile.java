package domain;

public interface KintaiRegisterRepositoryFile {
    void register(WorkDay workDay,WorkStartTime workStartTime, WorkEndTime workEndTime, WorkTime workTime,OverWorkTime overWorkTime);
    void countUp(TotallyMonth totallyMonth);
}
