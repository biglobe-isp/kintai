package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.KintaiLines;
import com.naosim.dddwork.domain.KintaiListRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class KintaiListRepositoryFile implements KintaiListRepository {

    @Override
    public KintaiLines get() throws IOException {
        File kintaiCsvFile = KintaiFile.getTargetCsv();

        try (
                FileReader fr = new FileReader(kintaiCsvFile);
                BufferedReader br = new BufferedReader(fr);
        ) {
            KintaiLines kintaiLines = new KintaiLines();
            String line;
            while ((line = br.readLine()) != null) {
                kintaiLines = kintaiLines.add(line);
            }
            return kintaiLines;
        }
    }
}
