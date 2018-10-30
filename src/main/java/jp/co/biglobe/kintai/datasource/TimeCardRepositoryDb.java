package jp.co.biglobe.kintai.datasource;

import jp.co.biglobe.kintai.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class TimeCardRepositoryDb implements TimeCardRepository {

    private static final int Csv_Column_Date_Index = 0;
    private static final int Csv_Column_Minutes_Index = 3;
    private static final int Csv_Column_OverWorkMinutes_Index = 4;

    private static final String FileName = "data.csv";

    @Override
    public Optional<MonthlyWorkTimeCard> findWorkTimeCard(final YearMonth yearMonth) {

        WorkTimeCardBuilder builder = new WorkTimeCardBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (!columns[Csv_Column_Date_Index].startsWith(yearMonth.getYearMonth())) {
                    continue;
                }
                DailyReport dailyReport = new DailyReport(Integer.valueOf(columns[Csv_Column_Minutes_Index]),
                        Integer.valueOf(columns[Csv_Column_OverWorkMinutes_Index]));

                builder.punch(columns[Csv_Column_Date_Index], dailyReport);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.build();
    }
}
