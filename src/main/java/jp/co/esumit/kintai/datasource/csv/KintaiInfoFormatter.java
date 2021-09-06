package jp.co.esumit.kintai.datasource.csv;

import javafx.util.converter.LocalDateTimeStringConverter;
import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.kintai_record.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime;
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime;
import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.WorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.ActualWorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.OvertimeMinutes;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class KintaiInfoFormatter {
    public String toCsvLine(KintaiRecord kintaiRecord) {
        return String.format("%s,%s,%s,%s,%s,%s\n"
                , kintaiRecord.getTargetDay().toString(DateTimeFormatter.ofPattern("yyyyMMdd"))
                , kintaiRecord.getStampingTimes().getStartTime().toString()
                , kintaiRecord.getStampingTimes().getEndTime().toString()
                , kintaiRecord.getActualWorkingMinutesValue()
                , kintaiRecord.getOvertimeValue()
                , kintaiRecord.getRegisteredTime().toString(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
    }

    public KintaiRecord toRecord(String[] columns){

        return new KintaiRecord(
                new TargetDay(columns[0]),
                new TimeCard(new StartTime(columns[1]), new EndTime(columns[2])),
                new WorkingMinutes(new ActualWorkingMinutes(Integer.parseInt(columns[3])),new OvertimeMinutes(Integer.parseInt(columns[4]))),
                new RegisteredTime(LocalDateTime.parse(columns[5], (DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))))
        );
    }
}
