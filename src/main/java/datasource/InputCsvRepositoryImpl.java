package datasource;

import domain.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InputCsvRepositoryImpl implements InputCsvRepository {

    public void InputCsvAdd(
            WorkDate workDate,
            StartTime startTime,
            EndTime endTime) {

        WorkTimeMinutes workTimeMinutes = new WorkTimeMinutes(startTime, endTime);
        OverWorkTimeMinutes overWorkTimeMinutes = new OverWorkTimeMinutes(workTimeMinutes);
        NowDate nowDate = new NowDate();

        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            // @todo Entityにする
            filewriter.write(
                    String.format("%s,%s,%s,%s,%s,%s\n",
                            workDate.workDate,
                            startTime.startTime,
                            endTime.endTime,
                            workTimeMinutes.workTimeMinutes,
                            overWorkTimeMinutes.overWorkMinutes,
                            nowDate.nowDate)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
