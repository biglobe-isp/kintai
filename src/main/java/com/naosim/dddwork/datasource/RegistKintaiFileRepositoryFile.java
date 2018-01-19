package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.RegistKintaiFileRepository;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class RegistKintaiFileRepositoryFile extends KintaiFileRepository implements RegistKintaiFileRepository {
    @Override
    public void execute(String registString) throws IOException {
        try(FileWriter filewriter = new FileWriter(this.file, true)) {
            filewriter.write(registString);
        }
    }
}
