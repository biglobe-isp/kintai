package com.naosim.dddwork.domain.daily_work;

import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class StartWorkTimeTest {
    @Test
    public void 勤務開始時間が9時00分以降の場合にエラーが出るか() {
        String expectedMessage = "勤務開始時間が9時00分を超えています。遅刻厳禁。";

        try {
            StartWorkTime inputStartTime = new StartWorkTime(LocalTime.of(9,1));

            System.out.println(inputStartTime.getValue().toString());
            fail("バリデーションが機能していない");
        }
        catch (Exception e) {
            assertThat(e.getMessage(), is(expectedMessage));
        }
    }
}
