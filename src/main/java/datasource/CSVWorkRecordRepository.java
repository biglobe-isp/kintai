package datasource;

import domain.entity.WorkRecord;
import domain.repository.WorkRecordRepositoryInterface;
import domain.value.*;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CSVWorkRecordRepository implements WorkRecordRepositoryInterface {
    private static final Path PATH = Paths.get("data.csv");

    public CSVWorkRecordRepository() {
        if (!Files.exists(PATH)) {
            try {
                Files.createFile(PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(@NonNull WorkRecord workRecord) {
        try (BufferedWriter out = Files.newBufferedWriter(PATH, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            out.write(toCSVFormat(workRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<WorkRecord> findByYearAndMonth(@NonNull WorkYearMonth workYearMonth) {
        Map<WorkDate, WorkRecord> dailyWorkRecordMap = new HashMap<>();
        try (BufferedReader in = Files.newBufferedReader(PATH, StandardCharsets.UTF_8)) {
            readCSVIntoMap(in, dailyWorkRecordMap, workYearMonth);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dailyWorkRecordMap.values();
    }

    private String toCSVFormat(@NonNull WorkRecord workRecord) {
        return String.format(
                "%s,%s,%s,%d,%d,%s\n",
                workRecord.getWorkDateString(),
                workRecord.getStartTimeString(),
                workRecord.getEndTimeString(),
                workRecord.computeWorkMinutes().getValue(),
                workRecord.computeOverWorkMinutes().getValue(),
                LocalDateTime.now().toString()
        );
    }

    private void readCSVIntoMap(@NonNull BufferedReader in, @NonNull Map<WorkDate, WorkRecord> dailyWorkRecordMap, @NonNull WorkYearMonth workYearMonth) throws IOException {
        String line;
        while ((line = in.readLine()) != null) {
            WorkDate workDate = toWorkDate(line);
            if (!workDate.belongs(workYearMonth)) {
                continue;
            }
            dailyWorkRecordMap.put(workDate, toWorkRecord(line));
        }
    }

    private WorkRecord toWorkRecord(@NonNull String line) {
        String[] columns = line.split(",");
        return new WorkRecord(toWorkDate(columns[0]), toWorkTime(columns[1], columns[2]));
    }

    private WorkDate toWorkDate(@NonNull String dateAsText) {
        int year = Integer.parseInt(dateAsText.substring(0, 4));
        int month = Integer.parseInt(dateAsText.substring(4, 6));
        int day = Integer.parseInt(dateAsText.substring(6, 8));
        return new WorkDate(year, month, day);
    }

    private WorkTime toWorkTime(@NonNull String startTimeAsText, @NonNull String endTimeAsText) {
        int startHour = Integer.parseInt(startTimeAsText.substring(0, 2));
        int startMinutes = Integer.parseInt(startTimeAsText.substring(2, 4));
        int endHour = Integer.parseInt(endTimeAsText.substring(0, 2));
        int endMinutes = Integer.parseInt(endTimeAsText.substring(2, 4));
        return new WorkTime(startHour, startMinutes, endHour, endMinutes);
    }
}
