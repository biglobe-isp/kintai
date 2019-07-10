package refactor.api;

import refactor.datasource.CsvFileRepository;
import refactor.domain.*;
import refactor.service.KintaiInputService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KintaiApi {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }

                Date date = createDate(args[1]);
                StartTime startTime = createStartTime(args[2]);
                EndTime endTime = createEndTime(args[3]);
                CurrentTime currentTime = new CurrentTime();
                Repository repository = new CsvFileRepository();

                KintaiInputService kintaiInputService = new KintaiInputService(
                        date, startTime, endTime, currentTime, repository);
                kintaiInputService.inputKintai();

            } else if ("total".equals(methodType)) {
                String yearMonth = args[1];
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                int totalWorkMinutes = 0;
                int totalOverWorkMinutes = 0;

                File file = new File("data.csv");

                try (
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                ) {

                    String line = br.readLine();
                    Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                    Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
                    while (line != null) {
                        String[] columns = line.split(",");
                        if (!columns[0].startsWith(yearMonth)) {
                            continue;
                        }
                        totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                        totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                        line = br.readLine();
                    }

                    Set<String> keySet = totalWorkMinutesMap.keySet();
                    for (String key : keySet) {
                        totalWorkMinutes += totalWorkMinutesMap.get(key);
                        totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
                    }

                    System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
                    System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
                }

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Date createDate(String yyyymmdd) {
        int year = Integer.valueOf(yyyymmdd.substring(0, 4));
        int month = Integer.valueOf(yyyymmdd.substring(4, 6));
        int day = Integer.valueOf(yyyymmdd.substring(6, 8));
        return new Date(year, month, day);
    }

    private static StartTime createStartTime(String hhmm) {
        int hour = Integer.valueOf(hhmm.substring(0, 2));
        int minute = Integer.valueOf(hhmm.substring(2, 4));
        return new StartTime(hour, minute);
    }

    private static EndTime createEndTime(String hhmm) {
        int hour = Integer.valueOf(hhmm.substring(0, 2));
        int minute = Integer.valueOf(hhmm.substring(2, 4));
        return new EndTime(hour, minute);
    }
}