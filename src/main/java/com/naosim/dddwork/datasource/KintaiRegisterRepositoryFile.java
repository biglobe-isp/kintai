package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.register.repository.KintaiRegisterRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class KintaiRegisterRepositoryFile implements KintaiRegisterRepository {

    @Override
    public void register(KintaiOfOneDay kintaiOfOneDay) {
        File kintaiCsvFile = KintaiFile.getTargetCsv();

        try (FileWriter filewriter = new FileWriter(kintaiCsvFile, true)) {
            filewriter.write(this.getLineString(kintaiOfOneDay));
        } catch (IOException e) {
            throw new RuntimeException("ファイルの書き込みに失敗しました");
        }
    }

    private String getLineString(KintaiOfOneDay kintaiOfOneDay) {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                kintaiOfOneDay.getWorkDate().getValue(),
                kintaiOfOneDay.getWorkStartTime().getValue(),
                kintaiOfOneDay.getWorkEndTime().getValue(),
                kintaiOfOneDay.getWorkMinutes().toString(),
                kintaiOfOneDay.getOverWorkMinutes().toString(),
                kintaiOfOneDay.getRegisterDateTime().toString()
        );
    }

}
