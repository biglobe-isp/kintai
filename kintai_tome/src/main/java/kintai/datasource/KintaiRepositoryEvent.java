package kintai.datasource;

import kintai.domain.AllBreakMinutes;
import kintai.domain.FixedMinutes;
import kintai.domain.HiKintai;
import kintai.domain.KintaiRepository;
import kintai.domain.OverWorkMinutes;
import kintai.domain.SyukkinDate;
import kintai.domain.SyukkinTime;
import kintai.domain.TaikinTime;
import kintai.domain.WorkMinutes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class KintaiRepositoryEvent implements KintaiRepository {

    @Override
    public void register(HiKintai hiKintai) throws IOException {

        File file = new File("data.csv");

        // todo 対象行に一致したら上書きってことができてない

        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(
                    String.format(
                            "%s,%s,%s,%s,%s,%s,%s\n",
                            hiKintai.getSyukkinDate().getValue(),
                            hiKintai.getSyukkinTime().getValue(),
                            hiKintai.getTaikinTime().getValue(),
                            hiKintai.getFixedMinutes().getValue(),
                            hiKintai.getOverWorkMinutes().getValue(),
                            hiKintai.getAllBreakMinutes().getValue(),
                            LocalDateTime.now().toString()));
        }

    }

    public void update(HiKintai hiKintai) throws IOException {

        File file = new File("data.csv");

        try (FileWriter filewriter = new FileWriter(file)) {
            filewriter.write(
                    String.format(
                            "%s,%s,%s,%s,%s,%s,%s\n",
                            hiKintai.getSyukkinDate().getValue(),
                            hiKintai.getSyukkinTime().getValue(),
                            hiKintai.getTaikinTime().getValue(),
                            hiKintai.getFixedMinutes().getValue(),
                            hiKintai.getOverWorkMinutes().getValue(),
                            hiKintai.getAllBreakMinutes().getValue(),
                            LocalDateTime.now().toString()));
        }

    }

    public List<HiKintai> refer(String yearMonth) throws IOException {

        List<HiKintai> list = new ArrayList<HiKintai>();

        File file = new File("data.csv");

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
        ) {

            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");

                HiKintai hiKintai = new HiKintai(
                        new SyukkinDate(columns[0]),
                        new SyukkinTime(columns[1]),
                        new TaikinTime(columns[2]),
                        new WorkMinutes(new FixedMinutes(Integer.valueOf(columns[3])), new OverWorkMinutes(Integer.valueOf(columns[4]))),
                        new AllBreakMinutes(Integer.valueOf(columns[5]))
                );

                list.add(hiKintai);
                line = br.readLine();
            }

            return list;
        }
    }
}