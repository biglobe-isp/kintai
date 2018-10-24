package refoctor.datasource;

import refoctor.domain.japan.*;

import java.io.File;
import java.io.FileWriter;

public class DayWorkMinutesDb implements DayWorkMinutesRepository {
    @Override
    public void dayInPut(DateDomain dateDomain, StartTime startTime, EndTime endTime, WorkTime workTime) {


        File file = new File("data.csv");

        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    dateDomain.getDate().getValue(),
                    startTime.getStart().getValue(),
                    endTime.getEnd().getValue(),
                    workTime.getWorkTime(),
                    workTime.getOverWork(),
                    dateDomain.getNowTime().getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
