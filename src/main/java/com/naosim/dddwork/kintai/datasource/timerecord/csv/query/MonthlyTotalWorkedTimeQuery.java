package com.naosim.dddwork.kintai.datasource.timerecord.csv.query;

import com.naosim.dddwork.kintai.api.settings.Environment;
import com.naosim.dddwork.kintai.datasource.timerecord.csv.domain.builder.DailyTimeRecordBuilder;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceDate;
import com.naosim.dddwork.kintai.domain.model.foundation.date.AttendanceYearMonth;
import com.naosim.dddwork.kintai.domain.model.timerecord.DailyWorkedTime;
import com.naosim.dddwork.kintai.domain.model.timerecord.collection.DailyWorkedTimeCollection;
import com.naosim.dddwork.kintai.domain.model.timerecord.derived.MonthlyTotalWorkedTime;
import com.naosim.dddwork.kintai.shared.exception.SystemException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * ［指定月の勤務時間合計］クエリ (CSVデータストア)
 */
public class MonthlyTotalWorkedTimeQuery {


    public MonthlyTotalWorkedTime totalWorkedTimeIn(AttendanceYearMonth yearMonth) {

        Path filePath = Paths.get(Environment.DATA_STORE_CSV_FILE_NAME);

        DailyWorkedTimeCollection dailyWorkedTimes = _extractDailyWorkedTimesIn(yearMonth, filePath);
        MonthlyTotalWorkedTime monthlyTotalWorkedTime = dailyWorkedTimes.total();
        return monthlyTotalWorkedTime;
    }


    /**
     * 指定された年月の日別勤務時間をCSVファイルから抽出する．
     * <pre>
     *     同一日付のデータについては、（追記方式なので）最後のデータを採取する。
     * </pre>
     *
     * @param yearMonth 抽出対象年月
     * @param csvFilePath CSVファイルパス
     *
     * @return 抽出した日別勤務時間のコレクション
     */
    private DailyWorkedTimeCollection _extractDailyWorkedTimesIn(AttendanceYearMonth yearMonth, Path csvFilePath) {

        final Map<AttendanceDate, DailyWorkedTime> dailyWorkedTimes;

        try (Stream<String> stream = Files.lines(csvFilePath, StandardCharsets.UTF_8)) {
            dailyWorkedTimes = stream
                    .map(line -> DailyTimeRecordBuilder.fromCsv(line))
                    .filter(timeRecord -> timeRecord.isIncludedIn(yearMonth))   // 指定年月分を抽出
                    .map(timeRecord -> timeRecord.calculateDetailedWorkTime())  // THINK: 実際は導出項目も記録済みだけど、ここでまた計算している...RDBかNoSQLかとか、イベントソーシングを使うのかなどによってもデータをどう記録してどう復元するのかは変わってくるのだろうか？
                    .collect(Collectors.toMap(
                            DailyWorkedTime::getAttendanceDate,
                            dailyWorkedTime -> dailyWorkedTime,
                            (earlier, latest) -> latest));                      // 追記方式なので後の方を選択
        }
        catch (IOException e) {
            throw new SystemException("CSVデータから復元時に IO例外発生．", e);
        }

        return DailyWorkedTimeCollection.of(dailyWorkedTimes);
    }
}
