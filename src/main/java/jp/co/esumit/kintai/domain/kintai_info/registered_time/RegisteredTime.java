package jp.co.esumit.kintai.domain.kintai_info.registered_time;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

@Value
@RequiredArgsConstructor
public class RegisteredTime {
    private final String value;

    public static RegisteredTime create() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String now = format.format(date);
        return new RegisteredTime(now);
    }
}
