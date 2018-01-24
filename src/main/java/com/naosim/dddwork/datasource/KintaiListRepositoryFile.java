package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.KintaiOfOneDayLine;
import com.naosim.dddwork.domain.KintaiOfOneDayLines;
import com.naosim.dddwork.domain.KintaiListRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class KintaiListRepositoryFile implements KintaiListRepository {

    @Override
    // TODO: IOExceptionを返すのではなく、RuntimeExceptionを返す
    public KintaiOfOneDayLines get() throws IOException {
        File kintaiCsvFile = KintaiFile.getTargetCsv();

        try (
                FileReader fr = new FileReader(kintaiCsvFile);
                BufferedReader br = new BufferedReader(fr);
        ) {
            List<KintaiOfOneDayLine> kintaiOfOneDayLineList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                kintaiOfOneDayLineList.add(new KintaiOfOneDayLine(line));
            }
            KintaiOfOneDayLines kintaiOfOneDayLines = new KintaiOfOneDayLines(kintaiOfOneDayLineList);
            return kintaiOfOneDayLines;
        }
    }
}
