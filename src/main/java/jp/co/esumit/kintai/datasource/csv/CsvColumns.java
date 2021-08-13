package jp.co.esumit.kintai.datasource.csv;

import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.OfficeMinutes;
import jp.co.esumit.kintai.domain.field.Overtime;
import jp.co.esumit.kintai.domain.field.RegisteredTime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import lombok.Value;

@Value
public class CsvColumns {

    TargetDay targetDay;
    StartTime startTime;
    EndTime endTime;
    OfficeMinutes officeMinutes;
    Overtime overtime;
    RegisteredTime registeredTime;

}
