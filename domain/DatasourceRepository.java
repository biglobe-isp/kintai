package domain;

public interface DatasourceRepository {
    public void writeData(String date, int start, int end, int workMinutes, int overWorkMinutes, String now);
    public int[] readData(String yearMonth);
}
