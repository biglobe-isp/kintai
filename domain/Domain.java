package domain;

import java.time.LocalDateTime;

public class Domain {

    public void registryData(KintaiToroku kintaiToroku, DatasourceRepository datasourceRepository) {
        datasourceRepository.writeData(kintaiToroku.getDate().getDate(),
                                        kintaiToroku.getStartHour().getStartHour(),
                                        kintaiToroku.getEndHour().getEndHour(),
                                        kintaiToroku.getWorkMinute().getWorkMinute(),
                                        kintaiToroku.getOverWorkMinute().getOverWorkMinute(),
                                        kintaiToroku.getNow().getNow());
    }

    public void displayData(KintaiHyoji kintaiHyoji, DatasourceRepository datasourceRepository) {
        System.out.println("勤務時間: " + kintaiHyoji.getTotalWorkMinute().getTotalWorkMinute() / 60 +
                            "時間" + kintaiHyoji.getTotalWorkMinute().getTotalWorkMinute() % 60 + "分");
        System.out.println("残業時間: " + kintaiHyoji.getTotalOverWorkMinute().getTotalOverWorkMinute() / 60 +
                            "時間" + kintaiHyoji.getTotalOverWorkMinute().getTotalOverWorkMinute() % 60 + "分");

    }

}