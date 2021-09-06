package jp.co.esumit.kintai.datasource.csv;

import jp.co.esumit.kintai.config.AppProperties;
import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvRepository implements KintaiRepository {
    private final AppProperties appProperties;
    private final KintaiInfoFormatter formatter;

    @Override
    public void write(KintaiRecord kintaiRecord) {

        try (FileWriter fw = new FileWriter(appProperties.getKintaiDataCsv(), true)) {
            fw.write(formatter.toCsvLine(kintaiRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<KintaiRecord> read(TargetYearMonth targetYM) {

        List<KintaiRecord> kintaiRecordList = new ArrayList<>();

        try (FileReader fr = new FileReader(appProperties.getKintaiDataCsv());
             BufferedReader br = new BufferedReader(fr)) {

            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(targetYM.getValue())) {

                    line = br.readLine();
                    continue;
                }

                kintaiRecordList.removeIf(KintaiRecord ->
                                                  columns[0] == KintaiRecord.getTargetDay().toString(DateTimeFormatter.ofPattern("yyyyMMdd")));
                kintaiRecordList.add(formatter.toRecord(columns));

                line = br.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return kintaiRecordList;
    }
}
