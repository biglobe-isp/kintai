package jp.co.esumit.kintai.datasource.csv;

import jp.co.esumit.kintai.domain.field.TotalOfficeMinutes;
import jp.co.esumit.kintai.domain.field.TotalOvertime;
import lombok.Value;

@Value
public class ConsoleOutput {

    TotalOfficeMinutes totalOfficeMinutes;
    TotalOvertime totalOvertime;
}
