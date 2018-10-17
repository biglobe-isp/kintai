package domain;

public class KintaiHyoji {

    private YearMonth yearMonth;
    private TotalWorkMinute totalWorkMinute;
    private TotalOverWorkMinute totalOverWorkMinute;
    private TotalNums totalNums;

    public KintaiHyoji(String yearMonth, DatasourceRepository datasourceRepository) {

        this.totalNums = new TotalNums(datasourceRepository.readData(yearMonth));
        //労働時間合計（M）を取得
        totalWorkMinute = new TotalWorkMinute(this.totalNums.getTotalNums()[0]);
        //残業時間合計（M）を取得
        totalOverWorkMinute = new TotalOverWorkMinute(this.totalNums.getTotalNums()[1]);
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }
    public TotalWorkMinute getTotalWorkMinute() {
        return totalWorkMinute;
    }
    public TotalOverWorkMinute getTotalOverWorkMinute() {
        return totalOverWorkMinute;
    }
    public TotalNums getTotalNums() {
        return totalNums;
    }

}
