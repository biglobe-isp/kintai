package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.KintaiRegistRepository;
import com.naosim.dddwork.domain.OneDayKintai;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class KintaiRegistRepositoryFile implements KintaiRegistRepository {

    @Override
    public void regist(OneDayKintai oneDayKintai) throws IOException {
        File kintaiCsvFile = KintaiFile.getTargetCsv();

        try (FileWriter filewriter = new FileWriter(kintaiCsvFile, true)) {
            filewriter.write(this.getLineString(oneDayKintai));
        }
    }

    private String getLineString(OneDayKintai oneDayKintai) {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                oneDayKintai.getWorkDate(), oneDayKintai.getStartTime(), oneDayKintai.getEndTime(),
                oneDayKintai.getWorkMinutes(), oneDayKintai.getOverWorkMinutes(), oneDayKintai.getNow()
        );
    }

}
