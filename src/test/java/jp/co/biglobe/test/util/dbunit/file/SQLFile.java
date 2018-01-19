package jp.co.biglobe.test.util.dbunit.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SQLFile {
    static final String SQL_EXTENSION = ".sql";

    private final String objectName;
    private final File file;

    public SQLFile(File file) {
        this.file = file;
        this.objectName = makeTableName(file.getName());
    }

    /**
     * ファイル名をオブジェクト名とする。
     * @param name
     * @return
     */
    private String makeTableName(String name){
        return name.substring(0, name.indexOf(SQL_EXTENSION));
    }

    public String getObjectName() {
        return objectName;
    }

    public File getFile() {
        return file;
    }

    /**
     * 対象ファイルのSQL文を取得する
     * @return
     * @throws java.io.IOException
     */
    public String getSql() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.getFile()));
        String sql = "";
        String str;
        while((str = br.readLine()) != null){
            sql += str;
        }
        br.close();
        return sql;
    }

}
