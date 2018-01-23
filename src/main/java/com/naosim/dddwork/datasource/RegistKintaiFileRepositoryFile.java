package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.RegistKintaiFileRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class RegistKintaiFileRepositoryFile implements RegistKintaiFileRepository {
    @Override
    public void execute(String registString) throws IOException {
        File kintaiCsvFile = KintaiFile.getCsvFile();

        try(FileWriter filewriter = new FileWriter(kintaiCsvFile, true)) {
            filewriter.write(registString);
        }
    }
}
