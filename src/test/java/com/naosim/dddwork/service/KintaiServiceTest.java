package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.InMemoryAttendanceRepository;
import com.naosim.dddwork.datasource.InMemoryRuleRepository;
import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.ResultTotal;
import com.naosim.dddwork.domain.Rule;
import com.naosim.dddwork.domain.RuleId;
import com.naosim.dddwork.domain.TimeSpan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class KintaiServiceTest {
    @Before
    public void setup() {
    }

    public static class _registTest {
        InMemoryAttendanceRepository attendanceRepository = new InMemoryAttendanceRepository();
        InMemoryRuleRepository ruleRepository = new InMemoryRuleRepository();

        @Before
        public void setup() {
            List<TimeSpan> rests = new ArrayList<>();
            rests.add(new TimeSpan("1200", "1300"));
            ruleRepository.add(new Rule(
                    new RuleId().toString(),
                    new TimeSpan("0900", "1800"),
                    "",
                    "20211130",
                    rests
            ));
        }

        // テスト実行できるかの確認までしたいだけなので、とりあえずテスト実装をちょっとだけ。
        // あとは余裕があれば。。。
        @Test
        public void _就業規約適用あり_正常登録_残業なし() throws Exception {

            KintaiService service = new KintaiService(attendanceRepository, ruleRepository);
            Attendance result = service.regist("20210901", "0900", "1800");
            assertEquals("20210901", result.getYyyymmdd());
            assertEquals("0900", result.getStarthhmm());
            assertEquals("1800", result.getEndhhmm());
            assertEquals(480, result.getWorkMinutes());
            assertEquals(0, result.getOrverWorkMinutes());
        }

        @Test
        public void _就業規約適用あり_正常登録_残業あり() throws Exception {

            KintaiService service = new KintaiService(attendanceRepository, ruleRepository);
            Attendance result = service.regist("20210901", "0900", "1900");
            assertEquals("20210901", result.getYyyymmdd());
            assertEquals("0900", result.getStarthhmm());
            assertEquals("1900", result.getEndhhmm());
            assertEquals(540, result.getWorkMinutes());
            assertEquals(60, result.getOrverWorkMinutes());
        }

        @Test
        public void _就業規約適用あり_正常登録_午前中だけ() throws Exception {

            KintaiService service = new KintaiService(attendanceRepository, ruleRepository);
            Attendance result = service.regist("20210901", "0800", "1200");
            assertEquals("20210901", result.getYyyymmdd());
            assertEquals("0800", result.getStarthhmm());
            assertEquals("1200", result.getEndhhmm());
            assertEquals(240, result.getWorkMinutes());
            assertEquals(0, result.getOrverWorkMinutes());
        }
    }

    public static class _totalTest {
        InMemoryAttendanceRepository attendanceRepository = new InMemoryAttendanceRepository();
        InMemoryRuleRepository ruleRepository = new InMemoryRuleRepository();
        KintaiService service = new KintaiService(attendanceRepository, ruleRepository);

        @Before
        public void setup() {
            List<TimeSpan> rests = new ArrayList<>();
            rests.add(new TimeSpan("1200", "1300"));
            ruleRepository.add(new Rule(
                    new RuleId().toString(),
                    new TimeSpan("0900", "1800"),
                    "",
                    "20211130",
                    rests
            ));
            service.regist("20210901", "0800", "1200");
            service.regist("20210902", "0900", "1800");
            service.regist("20210903", "0800", "2000");
        }

        @After
        public void end() {
            this.attendanceRepository.clear();
            this.ruleRepository.clear();
        }

        @Test
        public void _集計() throws Exception {
            attendanceRepository.printData();
            ResultTotal result = service.total("202109");
            assertEquals(1380, result.getTotalWorkMinutes());
            assertEquals(180, result.getTotalOverWorkMinutes());
        }

        @Test
        public void _集計_更新あり() throws Exception {
            service.regist("20210902", "0930", "1900"); // 上書き

            ResultTotal result = service.total("202109");
            assertEquals(1410, result.getTotalWorkMinutes());
            assertEquals(210, result.getTotalOverWorkMinutes());
        }
    }
}
