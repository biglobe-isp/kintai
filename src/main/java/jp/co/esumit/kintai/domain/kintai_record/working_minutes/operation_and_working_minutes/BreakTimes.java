package jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes;

import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Value
@Component
public class BreakTimes {
    List<BreakTime> breakTimes;

    public BreakTimes() {
        breakTimes = new ArrayList<>();
        breakTimes.add(BreakTime.create("1200", "1300"));
        breakTimes.add(BreakTime.create("1500", "1600"));
        breakTimes.add(BreakTime.create("1800", "1900"));
        breakTimes.add(BreakTime.create("2100", "2200"));
    }
}

