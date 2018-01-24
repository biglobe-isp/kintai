package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.KintaiRegistRepository;
import com.naosim.dddwork.domain.KintaiOfOneDay;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class KintaiRegistRepositoryFile implements KintaiRegistRepository {

    @Override
    public void regist(KintaiOfOneDay kintaiOfOneDay) throws IOException {
        File kintaiCsvFile = KintaiFile.getTargetCsv();

        try (FileWriter filewriter = new FileWriter(kintaiCsvFile, true)) {
            filewriter.write(this.getLineString(kintaiOfOneDay));
        }
    }

    private String getLineString(KintaiOfOneDay kintaiOfOneDay) {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                kintaiOfOneDay.getWorkDate(), kintaiOfOneDay.getStartTime(), kintaiOfOneDay.getEndTime(),
                kintaiOfOneDay.getWorkMinutes(), kintaiOfOneDay.getOverWorkMinutes(), kintaiOfOneDay.getNow()
        );
    }

}
