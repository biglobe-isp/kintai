package kintai.datasource;

import java.io.*;
import java.util.List;

public class ExportWorkingDateTotalRecordRepositoryDb {
    public String export() {
        String csvFilePath = "KintaiData.csv";
        String TotalRecord = null;
        try {
            FileReader reader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            TotalRecord = bufferedReader.readLine();

            List<String> list = List.of(TotalRecord.split(","));

        } catch (IOException e) {
            e.printStackTrace();


        }
        return TotalRecord;
    }
}
