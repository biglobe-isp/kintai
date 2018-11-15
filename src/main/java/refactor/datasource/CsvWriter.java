package refactor.datasource;

import refactor.domain.CsvWriterRepository;
import refactor.domain.Date;
import refactor.domain.EndTime;
import refactor.domain.NowTime;
import refactor.domain.StartTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter implements CsvWriterRepository {
    @Override
    public void registerWorkingTime(
            Date date, StartTime startTime, EndTime endTime, int workMinutes, int overWorkMinutes, NowTime nowTime) {
        java.io.File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    date.getDate(),
                    startTime.getStartTime(),
                    endTime.getEndTime(),
                    workMinutes,
                    overWorkMinutes,
                    nowTime.getNowTime()
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
