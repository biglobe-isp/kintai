package com.naosim.dddwork.service;

import com.google.common.collect.ImmutableList;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class WorkRegulationValidatorTest {

    @Test
    public void testValidateWorkTime1() {
        WorkRegulationValidator target = new WorkRegulationValidator();
        WorkRegulation workRegulation = createWorkRegulation();
        TimePoint startTime = TimePoint.of(9, 0);
        TimePoint endTime = TimePoint.of(18, 0);
        String expected = "";

        String actual = target.validateWorkTime(startTime, endTime, workRegulation);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testValidateWorkTime2() {
        WorkRegulationValidator target = new WorkRegulationValidator();
        WorkRegulation workRegulation = createWorkRegulation();
        TimePoint startTime = TimePoint.of(6, 30);
        TimePoint endTime = TimePoint.of(18, 0);
        String expected = "開始時間が早すぎます";

        String actual = target.validateWorkTime(startTime, endTime, workRegulation);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testValidateWorkTime3() {
        WorkRegulationValidator target = new WorkRegulationValidator();
        WorkRegulation workRegulation = createWorkRegulation();
        TimePoint startTime = TimePoint.of(11, 30);
        TimePoint endTime = TimePoint.of(18, 0);
        String expected = "開始時間が遅すぎます";

        String actual = target.validateWorkTime(startTime, endTime, workRegulation);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testValidateWorkTime4() {
        WorkRegulationValidator target = new WorkRegulationValidator();
        WorkRegulation workRegulation = createWorkRegulation();
        TimePoint startTime = TimePoint.of(9, 0);
        TimePoint endTime = TimePoint.of(12, 0);
        String expected = "終了時間が早すぎます";

        String actual = target.validateWorkTime(startTime, endTime, workRegulation);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testValidateWorkTime5() {
        WorkRegulationValidator target = new WorkRegulationValidator();
        WorkRegulation workRegulation = createWorkRegulation();
        TimePoint startTime = TimePoint.of(9, 0);
        TimePoint endTime = TimePoint.of(22, 30);
        String expected = "終了時間が遅すぎます";

        String actual = target.validateWorkTime(startTime, endTime, workRegulation);

        assertThat(actual).isEqualTo(expected);
    }

    private WorkRegulation createWorkRegulation() {
        return WorkRegulation.builder()
                .standardStartTime(TimePoint.of(9, 0))
                .standardEndTime(TimePoint.of(18, 0))
                .minStartTime(TimePoint.of(7, 0))
                .minEndTime(TimePoint.of(15, 0))
                .maxStartTime(TimePoint.of(11, 0))
                .maxEndTime(TimePoint.of(22, 0))
                .restTimes(ImmutableList.of(
                        TimeRange.of(12, 0, 13, 0),
                        TimeRange.of(18, 0, 19, 0),
                        TimeRange.of(21, 0, 22, 0)
                ))
                .standardWorkMinute(WorkMinute.of(8 * 60))              // 基準労働時間は8時間
                .build();
    }
}
