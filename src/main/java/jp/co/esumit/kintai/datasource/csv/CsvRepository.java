package jp.co.esumit.kintai.datasource.csv;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.office_minutes.OfficeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.kintai_info.registered_time.RegisteredTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvRepository implements KintaiRepository {
    final String csvFileName = "data.csv";

    @Override
    public void write(KintaiInfo kintaiInfo) {

        String date = kintaiInfo.getTargetDay().getValue();
        String start = kintaiInfo.getStartTime().toString();
        String end = kintaiInfo.getEndTime().toString();
        String officeMins = String.valueOf(kintaiInfo.getOfficeMinutesValue());
        String overtimeMins = String.valueOf(kintaiInfo.getOvertimeValue());
        String registeredTime = kintaiInfo.getRegisteredTime().getValue();

        try (FileWriter fw = new FileWriter(csvFileName, true)) {
            fw.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, officeMins, overtimeMins, registeredTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<KintaiInfo> read(TargetYearMonth targetYM) {

        List<KintaiInfo> targetColumn = new ArrayList<>();

        try (FileReader fr = new FileReader(new File(csvFileName));
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
                        new OfficeMinutes(Integer.parseInt(columns[3])),
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
