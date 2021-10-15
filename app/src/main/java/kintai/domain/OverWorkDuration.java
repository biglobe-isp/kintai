package kintai.domain;

import lombok.Value;

import java.time.Duration;

/**
 * 残業時間.
 */
@Value
public class OverWorkDuration {
    Duration duration;
}
