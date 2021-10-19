package kintai.datasource.util;

import java.io.File;

/**
 * ファイル生成器インターフェース.
 */
public interface FileCreator {
    File create(String fileName);
}
