package datasource;

import domain.IRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RepositoryDb implements IRepository {

    @Override
    public void writeData() {
//        String date = args[1];
//        String start = args[2];
//        String end = args[3];
//        String now = LocalDateTime.now().toString();

        //TODO


        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n", date, start, end, workMinutes, overWorkMinutes, now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void readData(IRepository iRepository) {

        int totalWorkMinutes = 0;
        int totalOverWorkMinutes = 0;

        String yearMonth = args[1];
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


        } catch (FileNotFoundException e1) {
            e1.printStackTrace();

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void readData(IRepository iRepository) {

    }
}
