package kintai.domain;

import lombok.Value;

import java.time.LocalTime;

@Value
public class BreakRange {
    LocalTime from;
    LocalTime to;
}
