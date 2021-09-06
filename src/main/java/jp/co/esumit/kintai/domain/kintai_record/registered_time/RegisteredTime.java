package jp.co.esumit.kintai.domain.kintai_record.registered_time;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Value
@Component
@RequiredArgsConstructor
public class RegisteredTime {
    LocalDateTime value;

    public RegisteredTime() {
        value = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public String toString(DateTimeFormatter format) {
        return value.format(format);
    }
}
