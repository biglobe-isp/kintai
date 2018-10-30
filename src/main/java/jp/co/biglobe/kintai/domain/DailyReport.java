package jp.co.biglobe.kintai.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DailyReport {

    @Getter
    private int minutes;
    @Getter
    private int overWorkMinutes;

}
