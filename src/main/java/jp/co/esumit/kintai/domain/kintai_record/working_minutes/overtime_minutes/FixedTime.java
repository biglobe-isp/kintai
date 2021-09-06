package jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes;

import lombok.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Value
@Component
public class FixedTime {
    Duration fixedTime;

    public FixedTime() {
        fixedTime = Duration.ofHours(8);
    }

    public int getMinutes() {
        return Math.toIntExact(fixedTime.toMinutes());
    }
}
