package jp.co.biglobe.test.util.dbunit.file;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DDLFileReader {

    private final List<SQLFile> files;

    public List<SQLFile> getFiles() {
        return files;
    }

    public DDLFileReader(String ddlPathDir) {
        this.files = new LinkedList<>();
        setFiles(ddlPathDir);
    }

    /**
     * 対象ディレクトリのファイルリストを取得
     * @param ddlPathDir
     */
    private void setFiles(String ddlPathDir) {
        File dir = new File(ddlPathDir);
        File[] filelist = dir.listFiles();
        if(filelist == null)return;
        for (File file : filelist) {
            if (file.isDirectory()) {
                setFiles(file.getPath());
            } else if (file.isFile()) {
                if (file.getName().contains(SQLFile.SQL_EXTENSION)) {
                    files.add(new SQLFile(file));
                }
            }
        }
    }
}
