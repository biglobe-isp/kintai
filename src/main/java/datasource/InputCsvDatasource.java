package datasource;

import domain.DateDomain;
import domain.EndDomain;
import domain.NowDomain;
import domain.StartDomain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InputCsvDatasource {
    public InputCsvDatasource(String[] inputData, int workMinutes, int overWorkMinutes){

        DateDomain dateDomain = new DateDomain(inputData[1]);
        StartDomain startDomain = new StartDomain(inputData[2]);
        EndDomain endDomain = new EndDomain(inputData[3]);
        NowDomain nowDomain = new NowDomain();

        File file = new File("data.csv");
        try(FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", dateDomain.date, startDomain.start, endDomain.end, workMinutes, overWorkMinutes, nowDomain.now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
