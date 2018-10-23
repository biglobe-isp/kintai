package domain.Japan;

public interface IRepository {

    public void writeData(DateVO date, StartVO start, EndVO end, ICalcWorkTimeVO iWorkTime, INowRepository iNowRepo);

    public void readData(DateVO date);
}
