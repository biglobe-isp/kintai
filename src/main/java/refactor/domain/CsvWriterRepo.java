package refactor.domain;

public interface CsvWriterRepo {
    void registerWorkingTime(String date, String start, String end, int workMinutes, int overWorkMinutes, String now);
}
