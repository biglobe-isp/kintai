package domain;

public interface DataSourceInterface {
    public void writeData(String date, String start, String end, int workMinutes, int overWorkMinutes, String now);
    public int[] readData(String yearMonth);
}
