package jp.co.esumit.kintai.domain.kintai_record.registered_time

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FixtureRegisteredTime {
    static RegisteredTime create() {
        return new RegisteredTime(LocalDateTime.parse("2021/01/01 00:00:00",
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
    }
}
