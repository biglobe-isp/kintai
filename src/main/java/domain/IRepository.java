package domain;

public interface IRepository {

    public void writeData(String[] args, int workMinutes, int overWorkMinutes);

    public void readData();
}
