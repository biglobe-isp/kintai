package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.IAttendanceRepository;
import com.naosim.dddwork.domain.IRuleRepository;
import com.naosim.dddwork.domain.Rule;
import com.naosim.dddwork.domain.ResultTotal;

import java.util.List;

public class KintaiService {
    private IAttendanceRepository attendanceRepository;
    private IRuleRepository ruleRepository;

    public KintaiService(IAttendanceRepository attendanceRepository, IRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance regist(String date, String start, String end) {
        System.out.println(String.format("service/regist params: date=%s start=%s end=%s", date, start, end));
        // 入力された日付に適用する就業規則を検索する
        Rule useRule = null;
        Attendance attendance = new Attendance(date, start, end);

        for (Rule rule : this.ruleRepository.getAll()) {
            if (rule.isValid(attendance.getTargetDate())) {
                useRule = rule;
            }
        }

        // 適用できる就業規則あれば適用させる
        if (useRule != null) {
            attendance = attendance.applyRule(useRule);
        }

        // 入力された勤怠を登録
        return attendanceRepository.save(attendance);
    }

    public ResultTotal total(String yearMonth) {
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        List<Attendance> readData = attendanceRepository.searchByYearMonth(yearMonth);

        for (Attendance attendance : readData) {
            totalWorkMinutes += attendance.getWorkMinutes();
            totalOverWorkMinutes += attendance.getOrverWorkMinutes();
        }

        return new ResultTotal(totalWorkMinutes, totalOverWorkMinutes);
    }
}