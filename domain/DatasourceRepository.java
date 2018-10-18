package domain;

public interface DatasourceRepository {
    void writeData(String date, int start, int end, int workMinutes, int overWorkMinutes, String now);
    int[] readData(String yearMonth);
}
