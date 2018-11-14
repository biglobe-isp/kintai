package refactor.datasource;

import refactor.domain.dto.Item.DateItem;
import refactor.domain.dto.Item.WorkMinutesList;
import refactor.domain.dto.WorkAndOverWorkMinutesList;
import refactor.domain.repository.AttendanceRepositorySelect;
import refactor.domain.dto.DisplayAttendanceEvent;
import refactor.domain.dto.Item.OverWorkMinutesList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class AttendanceRepositorySelectImpl implements AttendanceRepositorySelect {
    private final String FILE_NAME = "data.csv";
    private final String CSV_REGEX =",";

    public WorkAndOverWorkMinutesList select(DisplayAttendanceEvent data) {
        File file = new File(FILE_NAME);
        WorkAndOverWorkMinutesList outputData = null;

        try(FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            HashMap<DateItem, Integer > totalWorkMinutesMap = new HashMap<>();
            HashMap<DateItem, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(CSV_REGEX);
                if (!columns[0].startsWith(data.getYearMonth())) {
                    line = br.readLine();
                    continue;
                }
                DateItem date =new DateItem(columns[0]);
                totalWorkMinutesMap.put(date, Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(date, Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            outputData = new WorkAndOverWorkMinutesList(new WorkMinutesList(totalWorkMinutesMap),
                    new OverWorkMinutesList(totalOverWorkMinutesMap));

        }catch(Exception e){
            e.printStackTrace();
        }
        return outputData;
    }
}
