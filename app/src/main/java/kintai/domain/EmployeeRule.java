package kintai.domain;

import lombok.Value;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Value
public class EmployeeRule {
    List<BreakRange> breakRangeList;

    public EmployeeRule() {
        breakRangeList = new ArrayList<>();
        breakRangeList.add(new BreakRange(LocalTime.of(12,0),LocalTime.of(13,0)));
        breakRangeList.add(new BreakRange(LocalTime.of(18,0),LocalTime.of(19, 0)));
        breakRangeList.add(new BreakRange(LocalTime.of(21,0),LocalTime.of(22,0)));
    }
}
