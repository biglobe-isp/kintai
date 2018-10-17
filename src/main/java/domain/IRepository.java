package domain;

//import api.ArgsAndCheckVO; //
//import api.ArgsAndCheckVO;

public interface IRepository {

    public void writeData(DateVO dateVO, StartTimeVO startVO, EndTimeVO endVO, WorkTimeVO workVO);

    public void readData();
}
