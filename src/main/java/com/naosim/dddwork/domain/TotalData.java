package com.naosim.dddwork.domain;

import com.naosim.dddwork.datasource.TotalKintaiFileRepositoryFile;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TotalData extends ProcessData {

//    @Autowired
//    TotalKintaiFileRepository totalKintaiFileRepository;

    public TotalData(InputData inputData) {
        super(inputData);
    }

    @Override
    public void execute() throws IOException {
        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        TotalKintaiFileRepository totalKintaiFileRepository = new TotalKintaiFileRepositoryFile();
        List<String> list = totalKintaiFileRepository.execute();

        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        for (String line : list) {
            String[] columns = line.split(",");
            if(!columns[0].startsWith(inputData.getYearMonth())) {
                continue;
            }
            totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
            totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for(String key : keySet) {
            totalWorkMinutes += totalWorkMinutesMap.get(key);
            totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");

    }
}
