package refactor.datasource;

import refactor.api.form.InputTotalData;
import refactor.datasource.entity.AttendanceData;
import refactor.datasource.entity.WorkMinutesData;

import java.io.*;
import java.util.HashMap;

public class AttendanceRepository {

    private final String FILE_NAME = "data.csv";
    private final String CSV_REGEX =",";

    public void insert(AttendanceData data){
        File file = new File(FILE_NAME);
        try(FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    data.getDate(),
                    data.getStartTime(),
                    data.getEndTime(),
                    data.getWorkMinutes(),
                    data.getOverWorkMinutes(),
                    data.getNowTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WorkMinutesData select(InputTotalData data) {
        File file = new File(FILE_NAME);
        WorkMinutesData outputData = new WorkMinutesData();

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            HashMap<String, Integer> totalWorkMinutesMap = new HashMap<>();
            HashMap<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(",");
                if (!columns[0].startsWith(data.getYearMonth())) {
                    line = br.readLine();
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }

            outputData.setTotalWorkMinutes(totalWorkMinutesMap);
            outputData.setTotalOverWorkMinutes(totalOverWorkMinutesMap);

        }catch(Exception e){
            e.printStackTrace();
        }
        return outputData;
    }
}
