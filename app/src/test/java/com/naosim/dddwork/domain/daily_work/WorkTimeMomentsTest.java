package com.naosim.dddwork.domain.daily_work;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class WorkTimeMomentsTest {
    @Test
    public void 勤務開始時間が9時00分以降の場合にエラーが出るか() {
        ClockTime inputStartTime = new ClockTime(9,1);
        ClockTime inputEndTime = new ClockTime(18,0);
        String expectedMessage = "勤務開始時間が9時00分を超えています。遅刻厳禁。";

        try {
            WorkTimeMoments workTimeMoments = new WorkTimeMoments(inputStartTime, inputEndTime);

            System.out.println(workTimeMoments.getStartWorkTime().toString());
            fail("バリデーションが機能していない");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), is(expectedMessage));
        }
    }

    @Test
    public void 勤務開始時間が勤務終了時間より後の場合に正しいエラーが出るか() {
        ClockTime inputStartTime = new ClockTime(9,0);
        ClockTime inputEndTime = new ClockTime(8,59);
        String expectedMessage = "勤務終了時刻が勤務開始時刻よりも前に設定されています";

        try {
            WorkTimeMoments workTimeMoments = new WorkTimeMoments(inputStartTime, inputEndTime);

            fail("バリデーションが機能していない");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), is(expectedMessage));
        }
    }
}