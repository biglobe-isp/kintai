package domain;

public class KintaiHyoji {

    //取得したい勤怠年月（YYYYMM）
    private String yearMonth;


    public KintaiHyoji(String yearMonth ) {
        this.yearMonth = yearMonth;
    }

    public int getTotalWorkMinute(DatasourceRepository datasourceRepository) {
        TotalWorkMinute totalWorkMinute = new TotalWorkMinute(this.yearMonth, datasourceRepository);
        return totalWorkMinute.getTotalWorkMinute();
    }

    public int getTotalOverWorkMinute(DatasourceRepository datasourceRepository) {
        TotalOverWorkMinute totalOverWorkMinute = new TotalOverWorkMinute(this.yearMonth, datasourceRepository);
        return totalOverWorkMinute.getTotalOverWorkMinute();
    }

}
