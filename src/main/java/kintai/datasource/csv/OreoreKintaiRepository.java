package kintai.datasource.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import kintai.domain.kintai.Getsudo;
import kintai.domain.kintai.KinmuJikan;
import kintai.domain.kintai.Kinmubi;
import kintai.domain.kintai.Kintai;
import kintai.domain.kintai.KintaiRepository;
import kintai.domain.kintai.NyuryokuJikoku;
import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.domain.kintai.ZangyoJikan;

public class OreoreKintaiRepository implements KintaiRepository {

    private DateTimeFormatter kinmubiFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
    private DateTimeFormatter nyuryokuJikokuFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private DateTimeFormatter shukkinJikokuFormatter = DateTimeFormatter.ofPattern("HHmm");
    private DateTimeFormatter taikinJikokuFormatter = DateTimeFormatter.ofPattern("HHmm");

    public void store(Kintai kintai) {
        File file = new File("data.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter
                    .write(String.format("%s,%s,%s,%s,%s,%s\n", kinmubiFormat.format(kintai.getKinmubi().getKinmubi()),
                            kintai.getShukkinJikoku().getShukkinJikoku().format(this.shukkinJikokuFormatter),
                            kintai.getTaikinJikoku().getTaikinJikoku().format(this.taikinJikokuFormatter),
                            kintai.getKinmuJikan().getKinmuJikan().toMinutes(),
                            kintai.getZangyoJikan().getZangyoJikan().toMinutes(),
                            nyuryokuJikokuFormatter.format(kintai.getNyuryokuJikoku().getNyuryokuJikoku())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Kintai> KintaisOfGetsudo(Getsudo getsudo) {
        String yearMonth = getsudo.getGetsudo();
        File file = new File("data.csv");
        HashMap<String, Kintai> kintais = new HashMap<String, Kintai>();

        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
            String line = br.readLine();

            while (line != null) {
                String[] columns = line.split(",");

                if (columns.length != 6) {
                    throw new RuntimeException("データ不良です");
                }

                if (columns[0].startsWith(yearMonth)) {
                    Kintai kintai = new Kintai(new Kinmubi(LocalDate.parse(columns[0], this.kinmubiFormat)),
                            new ShukkinJikoku(LocalTime.parse(columns[1], this.shukkinJikokuFormatter)),
                            new TaikinJikoku(LocalTime.parse(columns[2], this.taikinJikokuFormatter)),
                            new KinmuJikan(Duration.ofMinutes(Integer.valueOf(columns[3]))),
                            new ZangyoJikan(Duration.ofMinutes(Integer.valueOf(columns[4]))),
                            new NyuryokuJikoku(LocalDateTime.parse(columns[5], nyuryokuJikokuFormatter)));
                    kintais.put(columns[0], kintai);
                }

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return kintais;
    }
}