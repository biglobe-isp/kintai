package jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes;

import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 拘束時間
 */
@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OperatingMinutes {
    int value;

    public static OperatingMinutes create(TimeCard timeCard) {

        return new OperatingMinutes(timeCard.getOperatingMinutes());
    }
}
