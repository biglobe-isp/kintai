package domain;

public interface DatasourceRepository {
    void writeData(String date, String start, String end, int workMinutes, int overWorkMinutes, String now);
    int[] readData(String yearMonth);
}
