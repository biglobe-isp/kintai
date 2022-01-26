package src.com.naosim.dddwork.datasource;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import src.com.naosim.dddwork.domain.*;


public class CsvOperat implements KintaiRepository{

    private static final String FILE_NAME = "data.csv";

    public void write(Regulation kintai) {

        try (FileWriter filewriter = new FileWriter(new File(FILE_NAME), true)) {
            filewriter.write(
                    String.format("%s,%s,%s,%s,%s,%s\n",
                            kintai.getWorkingDate().toDateStr(),
                            kintai.getStart().toStartTimeFormat(),
                            kintai.getEnd().toEndTimeFormat(),
                            kintai.getWorkTime().calcWorkTime(),
                            kintai.getOrverTime().calcOverTime(),
                            LocalDateTime.now().toString()));
        } catch (IOException e) {
            System.out.println("write処理にてIOExceptionが発生");
        }
    }

    public List<String[]> read() {
        List<String[]> valueLines = new ArrayList();

        try(
                FileReader fr = new FileReader(new File(FILE_NAME));
                BufferedReader br = new BufferedReader(fr);
        ) {
            String line = br.readLine();
            while(line != null){
                String[] columns = line.split(",");
                valueLines.add(columns);
                line = br.readLine();
            }
        } catch(IOException e) {
            System.out.println("read処理にてIOExceptionが発生");
        }
        return valueLines;
    }

}
