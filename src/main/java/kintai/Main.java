package kintai;

import kintai.datasource.WorkingDateTotalRecordRepositoryDb;
import kintai.domain.*;
import kintai.service.InputAttendanceService;
import kintai.service.MonthTotalWorkingTimeService;
import kintai.service.WorkingDateTotalRecordRepository;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class Main {
   //static WorkingDateTotalRecordRepository workingDateTotalRecordRepository;
   static WorkingDateTotalRecordRepositoryDb workingDateTotalRecordRepositoryDb = new WorkingDateTotalRecordRepositoryDb(Path.of("data.csv"));
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];
            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                /*if (!args[1].startsWith("-date:")) {
                    throw new RuntimeException("-date:から入力を開始して下さい");
                }
                if (!args[2].startsWith("-start:")) {
                    throw new RuntimeException("-start:から入力を開始して下さい");
                }
                if (!args[3].startsWith("-end:")) {
                    throw new RuntimeException("-end:から入力を開始して下さい");
                }*/

                String date = args[1];
                String start = args[2];
                String end = args[3];
                LocalDateTime now = LocalDateTime.now();

                InputAttendance inputAttendance = new InputAttendance(WorkDay.parseyyyyMMdd(date), WorkStart.parseHHmm(start), WorkEnd.parseHHmm(end));
                InputAttendanceService inputAttendanceService = new InputAttendanceService(workingDateTotalRecordRepositoryDb);
                //勤務実績の入力
                inputAttendanceService.input(inputAttendance, now);

            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                YearMonth month = YearMonth.parse(args[1], DateTimeFormatter.ofPattern("yyyyMM"));
                //args[1] ＝　201708想定
                MonthTotalWorkingTimeService monthTotalWorkingTimeService = new MonthTotalWorkingTimeService(workingDateTotalRecordRepositoryDb);
                //月間累計の勤務時間と残業時間の取得
                MonthTotalWorkingTime monthTotalWorkingTime = monthTotalWorkingTimeService.total(month);
                int totalWorkMinutes = monthTotalWorkingTime.getMonthTotalWorkMinutes().getValue();
                int totalOverWorkMinutes = monthTotalWorkingTime.getMonthTotalOverWorkMinutes().getValue();

                System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
                System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
