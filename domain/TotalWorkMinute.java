package domain;

public class TotalWorkMinute {
    private final int totalWorkMinute;

    TotalWorkMinute(String yearMonth, DatasourceRepository datasourceRepository){
        int[] totalNums;
        totalNums = datasourceRepository.readData(yearMonth);
        this.totalWorkMinute = totalNums[0];
    }

    public int getTotalWorkMinute() {
        return totalWorkMinute;
    }
}