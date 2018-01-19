package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.InputData;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class KintaiKanriService {

    public void execute(String[] args) throws IOException {

        InputData inputData = new InputData(args);

        File file = new File("data.csv");

        switch (inputData.getMethodType()) {
            case INPUT:

                int workMinutes = inputData.getEndH() * 60 + inputData.getEndM()
                        - (inputData.getStartH() * 60 + inputData.getStartM());

                if(inputData.getEndH() == 12) {
                    workMinutes -= inputData.getEndM();
                } else if(inputData.getEndH() >= 13) {
                    workMinutes -= 60;
                }

                if(inputData.getEndH() == 18) {
                    workMinutes -= inputData.getEndM();
                } else if(inputData.getEndH() >= 19) {
                    workMinutes -= 60;
                }

                if(inputData.getEndH() == 21) {
                    workMinutes -= inputData.getEndM();
                } else if(inputData.getEndH() >= 22) {
                    workMinutes -= 60;
                }

                int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
                try(FileWriter filewriter = new FileWriter(file, true)) {
                    filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                            inputData.getDate(), inputData.getStartTime(), inputData.getEndTime(),
                            workMinutes, overWorkMinutes, inputData.getNow()));
                }

                break;

            case TOTAL:
                int totalWorkMinutes = 0;
                int totalOverWorkMinutes = 0;

                try(
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                ) {

                    String line = br.readLine();
                    Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                    Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
                    while(line != null){
                        String[] columns = line.split(",");
                        if(!columns[0].startsWith(inputData.getYearMonth())) {
                            continue;
                        }
                        totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                        totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                        line = br.readLine();
                    }

                    Set<String> keySet = totalWorkMinutesMap.keySet();
                    for(String key : keySet) {
                        totalWorkMinutes += totalWorkMinutesMap.get(key);
                        totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
                    }

                    System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
                    System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
                }
                break;

            default:
        }
    }
}
