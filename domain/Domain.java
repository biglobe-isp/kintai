package domain;

public class Domain {

    public void registryData(KintaiToroku kintaiToroku, DatasourceRepository datasourceRepository) {
        datasourceRepository.writeData(kintaiToroku.getDate(),
                                        kintaiToroku.getStartHourStr() + kintaiToroku.getStartMinuteStr(),
                                        kintaiToroku.getEndHourStr() + kintaiToroku.getEndMinuteStr(),
                                        kintaiToroku.getWorkMinute(),
                                        kintaiToroku.getOverWorkMinute(),
                                        kintaiToroku.getNow());
    }

    public void displayData(KintaiHyoji kintaiHyoji, DatasourceRepository datasourceRepository) {

        int totalWorkMinute = kintaiHyoji.getTotalWorkMinute(datasourceRepository);
        int totalOverWorkMinute = kintaiHyoji.getTotalOverWorkMinute(datasourceRepository);

        System.out.println("勤務時間: " + totalWorkMinute / 60 + "時間" + totalWorkMinute % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinute / 60 + "時間" + totalOverWorkMinute % 60 + "分");

    }

}