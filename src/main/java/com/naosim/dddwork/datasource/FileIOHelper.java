package com.naosim.dddwork.datasource;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
public class FileIOHelper {

    private final static Charset CHARSET = StandardCharsets.UTF_8;

    void writeLines(String filepath, List<String> lines) {
        File file = new File(filepath);
        try {
            Files.write(
                    file.toPath(),
                    lines,
                    CHARSET,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<String> readLines(String filepath) {
        File file = new File(filepath);
        try {
            return Files.readAllLines(file.toPath(), CHARSET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
