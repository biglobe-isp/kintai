package jp.co.biglobe.kintai.datasource;

import jp.co.biglobe.kintai.datasource.format.WorkTimeFormatter;
import jp.co.biglobe.kintai.domain.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


public class WorkTimeRepositoryDb implements WorkTimeRepository {


    private static final String FileName = "data.csv";

    @Override
    public void input(final WorkTime workTime) {
        File file = new File(FileName);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(WorkTimeFormatter.format(workTime));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
