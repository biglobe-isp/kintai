package jp.co.biglobe.kintai.datasource;

import jp.co.biglobe.kintai.datasource.format.WorkTimeFormatter;
import jp.co.biglobe.kintai.domain.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


public class WorkTimeRepositoryDb implements WorkTimeRepository {

    private static final int Csv_Column_Date_Index = 0;
    private static final int Csv_Column_Minutes_Index = 3;
    private static final int Csv_Column_OverWorkMinutes_Index = 4;

    private static final String FileName = "data.csv";

    @Override
    public void input(final WorkTime workTime) {
        File file = new File(FileName);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(WorkTimeFormatter.format(workTime));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MonthlyWorkTimeCard> findWorkTimeCard(final YearMonth yearMonth) {

        MonthlyWorkTimeCard timeCard = new MonthlyWorkTimeCard();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (!columns[Csv_Column_Date_Index].startsWith(yearMonth.getYearMonth())) {
                    continue;
                }
                WorkTime workTime = new WorkTime(Integer.valueOf(columns[Csv_Column_Minutes_Index]),
                        Integer.valueOf(columns[Csv_Column_OverWorkMinutes_Index]));

                // ビルダーで作って
                timeCard.punch(columns[Csv_Column_Date_Index], workTime);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return timeCard.isPunched() ? Optional.ofNullable(timeCard) : Optional.empty();
    }
}
