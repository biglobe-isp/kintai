package domain;

public interface KintaiRegisterRepositoryFile {
    public void regist(WorkDay workDay,WorkStartTime workStartTime, WorkEndTime workEndTime, WorkTime workTime,OverWorkTime overWorkTime);
    public void countUp(String value);
}
