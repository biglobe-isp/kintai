package domain;

public class TotalOverWorkMinute {
    private final int totalOverWorkMinute;

    TotalOverWorkMinute(String yearMonth, DatasourceRepository datasourceRepository){
        int[] totalNums;
        totalNums = datasourceRepository.readData(yearMonth);
        this.totalOverWorkMinute = totalNums[1];
    }

    public int getTotalOverWorkMinute() {
        return totalOverWorkMinute;
    }
}