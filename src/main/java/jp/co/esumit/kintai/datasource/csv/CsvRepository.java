package jp.co.esumit.kintai.datasource.csv;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.operation_and_working_minutes.ActualWorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CsvRepository implements KintaiRepository {
    final String csvFileName = "data.csv";

    @Override
    public void write(KintaiInfo kintaiInfo) {

        StringJoiner sj = new StringJoiner("\n");

        try (FileReader fr = new FileReader(csvFileName);
             BufferedReader br = new BufferedReader(fr)) {

            String line = br.readLine();
            while (line != null) {
                if (!line.startsWith(kintaiInfo.getTargetDay().getValue())) {
                    sj.add(line);
                    //NOTE:対象日付:targetDayが一致する行以外をsjに値を渡す
                    // 　→対象日付が一致するものは行を削除。
                }
                line = br.readLine();
            }
            sj.add(kintaiInfo.toStringWithComma());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //値をCSVに出力する。
            flush(sj.toString());
        }
    }

    private void flush(String contents) {

        try (FileWriter fw = new FileWriter(csvFileName)) {
            fw.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<KintaiInfo> read(TargetYearMonth targetYM) {

        List<KintaiInfo> targetColumn = new ArrayList<>();

        try (FileReader fr = new FileReader(csvFileName);
             BufferedReader br = new BufferedReader(fr)) {

            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(targetYM.getValue())) {

                    line = br.readLine();
                    continue;
                }

                KintaiInfo kintaiInfo = new KintaiInfo(
                        new TargetDay(columns[0]),
                        new StartTime(columns[1]),
                        new EndTime(columns[2]),
                        new ActualWorkingMinutes(Integer.parseInt(columns[3])),
                        new OvertimeMinutes(Integer.parseInt(columns[4])),
                        new RegisteredTime(columns[5])
                );

                targetColumn.add(kintaiInfo);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return targetColumn;
    }
}
