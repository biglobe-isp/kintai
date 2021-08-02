package com.naosim.dddwork.service;

import java.util.*;

import com.naosim.dddwork.datasource.CsvDb;
import com.naosim.dddwork.domain.Rule;
import com.naosim.dddwork.domain.TimeSpan;
import com.naosim.dddwork.domain.Attendance;

public class KintaiService {

    private Rule[] rules = new Rule[2];

    public KintaiService() {
        // 勤怠サービスで使う就業規則情報をとりあえずここのコンストラクタで作ってます
        TimeSpan workingHours1 = new TimeSpan("0900", "1800");
        List<TimeSpan> rests1 = new ArrayList<>();
        rests1.add(new TimeSpan("1200", "1300"));
        rests1.add(new TimeSpan("1800", "1900"));
        rests1.add(new TimeSpan("2100", "2200"));
        this.rules[0]= new Rule(workingHours1, null, "20170114", rests1);
        // 来月15日を2017/01/15と仮定してルールを追加
        TimeSpan workingHours2 = new TimeSpan("0900", "1800");
        List<TimeSpan> rests2 = new ArrayList<>();
        rests2.add(new TimeSpan("1200", "1300"));
        rests2.add(new TimeSpan("1500", "1600"));
        rests2.add(new TimeSpan("1800", "1900"));
        rests2.add(new TimeSpan("2100", "2200"));
        this.rules[1]= new Rule(workingHours2, "20170115", null, rests2);
    }
    public void regist(String date, String start, String end) {
        System.out.println(String.format("service/regist params: date=%s start=%s end=%s", date, start, end));
        // 入力された日付に適用する就業規則を検索する
        Rule useRule = null;
        Attendance attendance = new Attendance(date, start, end);

        for (Rule rule: this.rules) {
            if (rule.isValid(attendance.getTargetDate())) {
                useRule = rule;
            }
        }

        // 適用できる就業規則がない
        if (useRule == null) throw new RuntimeException("入力された日付に適用できる就業規則がないので登録できません");

        // 就業規則を適用させる
        Attendance applyedAttendance = attendance.applyRule(useRule);

        // 入力された勤怠を登録
        CsvDb db = new CsvDb();
        String[] items = { date, start, end,
                String.valueOf(applyedAttendance.getWorkMinutes()),
                String.valueOf(applyedAttendance.getOrverWorkMinutes())};

        try {
            db.add(items);
        } catch (Exception e) {
            throw e;
        }

    }

    public void total(String yearMonth) {
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;
        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        CsvDb db = new CsvDb();
        List<String[]> readData = db.read();

        for(String[] columns : readData){

            if(!columns[0].startsWith(yearMonth)) {
                continue;
            }
            totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
            totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for(String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }
}