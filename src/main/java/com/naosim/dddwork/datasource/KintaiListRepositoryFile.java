package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.kintai.KintaiListRepository;
import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.KintaiOfOneDays;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class KintaiListRepositoryFile implements KintaiListRepository {

    @Override
    public KintaiOfOneDays get() {
        File kintaiCsvFile = KintaiFile.getTargetCsv();

        try (
                FileReader fr = new FileReader(kintaiCsvFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            List<KintaiOfOneDay> kintaiOfOneDayLineList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                kintaiOfOneDayLineList.add(new KintaiOfOneDay(line));
            }
            KintaiOfOneDays kintaiOfOneDays = new KintaiOfOneDays(kintaiOfOneDayLineList);
            return kintaiOfOneDays;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("対象ファイルが存在しません");
        } catch (IOException e) {
            throw new RuntimeException("ファイルの読み込みに失敗しました");
        }
    }
}
