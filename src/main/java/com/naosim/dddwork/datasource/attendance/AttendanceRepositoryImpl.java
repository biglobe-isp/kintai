package com.naosim.dddwork.datasource.attendance;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceHistory;
import com.naosim.dddwork.domain.attendance.AttendanceList;
import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import com.naosim.dddwork.domain.attendance.DutyTime;
import com.naosim.dddwork.domain.attendance.OverWorkMinutes;
import com.naosim.dddwork.domain.attendance.WorkDate;
import com.naosim.dddwork.domain.attendance.WorkMinutes;
import jp.co.biglobe.lib.publication.date.DateFormatter;
import jp.co.biglobe.lib.publication.date.DateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 2018/09/27 新規作成
 * 2018/09/28 重複読み出し防止に対応
 * 2018/09/28 レビュー指摘事項反映 find()からAttendanceHistoryを返却するように修正
 */
@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepository {

    @Override
    public void input(Attendance attendance) throws IOException {

        File file = new File("data.csv");

        FileWriter filewriter = new FileWriter(file, true);

        filewriter.write(
                String.format(
                        "%s,%s,%s,%s,%s,%s\n",
                        DateFormatter.format_yyyyMMdd(attendance.getWorkDate().getValue()),
                        DateFormatter.format_HHmmss(attendance.getDutyTime().getStartTime()).substring(0, 4),
                        DateFormatter.format_HHmmss(attendance.getDutyTime().getEndTime()).substring(0, 4),
                        attendance.getWorkMinutes().getValue(),
                        attendance.getOverWorkMinutes().getValue(),
                        LocalDateTime.now().toString()));

        System.out.println(
                String.format(
                        "%s,%s,%s,%s,%s,%s\n",
                        DateFormatter.format_yyyyMMdd(attendance.getWorkDate().getValue()),
                        DateFormatter.format_HHmmss(attendance.getDutyTime().getStartTime()).substring(0, 4),
                        DateFormatter.format_HHmmss(attendance.getDutyTime().getEndTime()).substring(0, 4),
                        attendance.getWorkMinutes().getValue(),
                        attendance.getOverWorkMinutes().getValue(),
                        LocalDateTime.now().toString()));
    }

    @Override
    public AttendanceHistory find() throws IOException {

        File file = new File("data.csv");

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Map<String, String[]> noDuplicationMap = new LinkedHashMap<>();

        bufferedReader.lines()
                .map(line -> line.split(","))
                .forEach(columns ->
                        noDuplicationMap.put(columns[0], columns)
                );

        return new AttendanceHistory(
                new AttendanceList(
                        noDuplicationMap.values().stream()
                                .map(columns ->
                                        new Attendance(
                                                new WorkDate(DateParser.parse_yyyyMMdd(columns[0])),
                                                new DutyTime(DateParser.parse_HHmmss(columns[1] + "00"),
                                                        DateParser.parse_HHmmss(columns[2] + "00")),
                                                new WorkMinutes(Integer.parseInt(columns[3])),
                                                new OverWorkMinutes(Integer.parseInt(columns[4]))
                                        )
                                )
                                .collect(Collectors.toList())
                )
        );
    }
}
