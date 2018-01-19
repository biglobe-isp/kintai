package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.TotalKintaiFileRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TotalKintaiFileRepositoryFile extends KintaiFileRepository implements TotalKintaiFileRepository {

    @Override
    public List<String> execute() throws IOException {
        try(
                FileReader fr = new FileReader(this.file);
                BufferedReader br = new BufferedReader(fr);
        ) {
            List<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            return list;
        }
    }
}
