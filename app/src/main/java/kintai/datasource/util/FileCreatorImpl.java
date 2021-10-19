package kintai.datasource.util;

import java.io.File;

/**
 * ファイル生成器.
 */
public class FileCreatorImpl implements FileCreator{
    @Override
    public File create(String fileName) {
        return new File(fileName);
    }
}
