package com.naosim.dddwork.datasource.attendance;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceList;
import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.OverWorkMinutes;
import com.naosim.dddwork.domain.attendance.StartTime;
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
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepository {

    @Override
    public void input(Attendance attendance) {

        File writeFile = new File("data_tmp.csv");

        File readFile = new File("data.csv");

        try (FileWriter filewriter = new FileWriter(writeFile, true)) {

            try (
                    FileReader fileReader = new FileReader(readFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader)
            ) {

                // 重複日付行を除外した一時ファイルを作成
                bufferedReader.lines()
                        .map(line -> line.split(","))
                        .forEach(columns ->
                                {
                                    try {
                                        if (!DateFormatter.format_yyyyMMdd(attendance.getWorkDate().getValue())
                                                .equals(columns[0])) {

                                            filewriter.write(
                                                    String.format(
                                                            "%s,%s,%s,%s,%s,%s\n",
                                                            columns[0],
                                                            columns[1],
                                                            columns[2],
                                                            columns[3],
                                                            columns[4],
                                                            columns[5])
                                            );
                                        }
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }
                                }
                        );

            } catch (Exception exception) {
                exception.printStackTrace();
            }

            // 一時ファイルに新規行を追加
            filewriter.write(
                    String.format(
                            "%s,%s,%s,%s,%s,%s\n",
                            DateFormatter.format_yyyyMMdd(attendance.getWorkDate().getValue()),
                            DateFormatter.format_HHmmss(attendance.getStartTime().getValue()).substring(0, 4),
                            DateFormatter.format_HHmmss(attendance.getEndTime().getValue()).substring(0, 4),
                            attendance.getWorkMinutes().getValue(),
                            attendance.getOverWorkMinutes().getValue(),
                            LocalDateTime.now().toString()));

            System.out.println(
                    String.format(
                            "%s,%s,%s,%s,%s,%s\n",
                            DateFormatter.format_yyyyMMdd(attendance.getWorkDate().getValue()),
                            DateFormatter.format_HHmmss(attendance.getStartTime().getValue()).substring(0, 4),
                            DateFormatter.format_HHmmss(attendance.getEndTime().getValue()).substring(0, 4),
                            attendance.getWorkMinutes().getValue(),
                            attendance.getOverWorkMinutes().getValue(),
                            LocalDateTime.now().toString()));

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // 元ファイルを削除し、一時ファイルを保存ファイル名にリネーム
        if (!readFile.delete()) {
            System.out.println("ファイル削除に失敗しました。");
        }

        if (!writeFile.renameTo(readFile)) {
            System.out.println("ファイルのリネームに失敗しました。");
        }
    }

    @Override
    public Optional<AttendanceList> find() {

        File file = new File("data.csv");

        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {

            return Optional.of(
                    new AttendanceList(
                            bufferedReader.lines()
                                    .map(line -> line.split(","))
                                    .map(columns ->
                                            new Attendance(
                                                    new WorkDate(DateParser.parse_yyyyMMdd(columns[0])),
                                                    new StartTime(DateParser.parse_HHmmss(columns[1] + "00")),
                                                    new EndTime(DateParser.parse_HHmmss(columns[2] + "00")),
                                                    new WorkMinutes(Integer.parseInt(columns[3])),
                                                    new OverWorkMinutes(Integer.parseInt(columns[4]))
                                            )
                                    )
                                    .collect(Collectors.toList())
                    )
            );

        } catch (Exception exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}
