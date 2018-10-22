package domain;

//import api.ArgsCheckVO; //

public interface IRepository {

    public void writeData(DateVO date, StartVO start, EndVO end, CalcWorkTimeVO workTime, INowRepository iNowRepo);

    public void readData();
}
