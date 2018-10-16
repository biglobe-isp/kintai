package datasource;

import domain.IRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

//package datasource;

public class RepositoryDb implements IRepository {

    @Override
    public void writeData(String[] args, int workMinutes, int overWorkMinutes) {
        String date = args[1];
        String start = args[2];
        String end = args[3];
        String now = LocalDateTime.now().toString();


        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void readData() {


    }


}
