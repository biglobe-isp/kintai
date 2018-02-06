package jp.co.biglobe.test.util.dbunit;

import jp.co.biglobe.test.util.dbunit.file.DDLFileReader;
import jp.co.biglobe.test.util.dbunit.file.SQLFile;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * DB接続クラス。最初だけDDL文を実行するため、Singletonとなっている。
 */
public class DbConnector {

    private final DbUnitTesterProperty dbUnitTesterProperty;

    // DB接続オブジェクト
    private DataSource dataSource = null;

    public DbConnector(DataSource dataSource, DbUnitTesterProperty dbUnitTesterProperty) {
        this.dbUnitTesterProperty = dbUnitTesterProperty;
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * コネクションオブジェクトを取得する
     *
     * @return
     * @throws SQLException
     * @throws DatabaseUnitException
     */
    public IDatabaseConnection getDbConnection() throws SQLException, DatabaseUnitException {
        Connection conn = getConnection();
        return new DatabaseConnection(conn);
    }

    /**
     * SQL文の実行
     *
     * @param sql
     * @throws Exception
     */
    public void executeQuery(String sql) throws SQLException {
        Connection conn = null;
        try {
            conn = getDbConnection().getConnection();
            conn.createStatement().execute(sql);
            conn.commit();
        } catch (SQLException e) {
        } catch (DatabaseUnitException e) {
        } finally {
            conn.close();
        }
    }

    public static void executeQuery(Connection conn, String sql) throws SQLException {
        conn.createStatement().execute(sql);
    }

    public void executeQueryThrowException(String sql) throws SQLException {
        Connection conn = null;
        try {
            conn = getDbConnection().getConnection();
            conn.createStatement().execute(sql);
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + sql);
        } catch (DatabaseUnitException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            conn.close();
        }
    }

    public void executeQueryThrowException(Connection conn, String sql) {
        try {
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + sql);
        }
    }

    /**
     * ファイルリストのSQL文を実行する
     * @param ddlFileReader
     */
    public void ddlExecute(DDLFileReader ddlFileReader) {
        List<SQLFile> files = ddlFileReader.getFiles();
        for (SQLFile file : files) {
            String sql = null;
            try {
                sql = file.getSql();
                LoggerFactory.getLogger("testdayo----").debug(sql);
                executeQuery(sql);
            } catch (IOException | SQLException e) {
                // 何もしない
            }
        }
    }

    public void ddlExecuteWithoutCommit(DDLFileReader ddlFileReader) {
        List<SQLFile> files = ddlFileReader.getFiles();

        Connection conn = null;
        try {
            conn = getDbConnection().getConnection();
            for (SQLFile file : files) {
                try {
                    String sql = file.getSql();
                    LoggerFactory.getLogger("testdayo----").debug(sql);
                    executeQuery(conn, sql);
                } catch (IOException | SQLException e) {
                    // 何もしない
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * ファイルリストのSQL文を実行する
     * CreateTable実行時に、H2だとDateをTimeStamp型に変換する。
     * 理由は、H2のDateは年月日なので、OracleのDateと型が不一致。
     * 年月日時分秒でデータを登録すれば、H2とOracleで差はない。
     * @param ddlFileReader
     */
    public void ddlExecuteForCreateTable(DDLFileReader ddlFileReader, String dbName) {
        List<SQLFile> files = ddlFileReader.getFiles();
        for (SQLFile file : files) {
            String sql = null;
            try {
                if(dbName.equals("h2")) {
                    sql = file.getSql().replaceAll("DATE", "TIMESTAMP");
                }else{
                    sql = file.getSql();
                }
                LoggerFactory.getLogger("testdayo----").debug(sql);
                executeQuery(sql);
            } catch (IOException | SQLException e) {
                // 何もしない
            }
        }
    }

    /**
     * ddlFileReaderにあるファイル名から指定したSQL文を実行する
     * @param ddlFileReader
     * @param Sql
     */
    private void executeSQLFromFileName(DDLFileReader ddlFileReader, String Sql) {
        List<SQLFile> files = ddlFileReader.getFiles();
        for (SQLFile file : files) {
            String sql = Sql + " " + file.getObjectName();
            try {
                executeQuery(sql);
            } catch (SQLException e) {
                // Dropはエラーが起きても握りつぶします
            }
        }
    }

    private void executeSQLFromFileName(Connection conn, DDLFileReader ddlFileReader, String Sql) {
        List<SQLFile> files = ddlFileReader.getFiles();
        for (SQLFile file : files) {
            String sql = Sql + " " + file.getObjectName();
            try {
                executeQuery(conn, sql);
            } catch (SQLException e) {
                // Dropはエラーが起きても握りつぶします
            }
        }
    }

    private void executeSQLFromFileNameThrowException(DDLFileReader ddlFileReader, String Sql) {
        List<SQLFile> files = ddlFileReader.getFiles();
        for (SQLFile file : files) {
            String sql = Sql + " " + file.getObjectName();
            try {
                executeQueryThrowException(sql);
            } catch (SQLException e) {
                // Dropはエラーが起きても握りつぶします
            }
        }
    }

    private void executeSQLFromFileNameThrowException(Connection conn, DDLFileReader ddlFileReader, String Sql) {
        List<SQLFile> files = ddlFileReader.getFiles();
        for (SQLFile file : files) {
            String sql = Sql + " " + file.getObjectName();
            executeQueryThrowException(conn, sql);
        }
    }

    private void executeSQLFromFileName(DDLFileReader ddlFileReader, String Sql, String Sql2) {
        List<SQLFile> files = ddlFileReader.getFiles();
        for (SQLFile file : files) {
            String sql = Sql + " " + file.getObjectName() + " " + Sql2;
            try {
                executeQuery(sql);
            } catch (SQLException e) {
                // Dropはエラーが起きても握りつぶします
            }
        }
    }

    /**
     * 作成したオブジェクト（テーブル）をDROPする。オブジェクトがテーブルだった場合のみ有効。
     * @param ddlFileReader
     */
    public void executeDropTable(DDLFileReader ddlFileReader) {
        executeSQLFromFileName(ddlFileReader, "DROP TABLE", "CASCADE CONSTRAINT");
    }

    /**
     * 作成したオブジェクト（テーブル）をDROPする。オブジェクトがテーブルだった場合のみ有効。
     * @param ddlFileReader
     */
    public void executeTruncateTable(DDLFileReader ddlFileReader) {
        executeSQLFromFileName(ddlFileReader, "TRUNCATE TABLE");
        executeSQLFromFileNameThrowException(ddlFileReader, "TRUNCATE TABLE");
    }

    /**
     * 作成したオブジェクト（シーケンス）をDROPする。オブジェクトがシーケンスだった場合のみ有効。
     * @param ddlFileReader
     */
    public void executeDropSequence(DDLFileReader ddlFileReader) {
        executeSQLFromFileName(ddlFileReader, "DROP SEQUENCE");
    }

    public void executeDeleteTable(DDLFileReader ddlFileReader) {
        Connection conn = null;
        try {
            conn = getDbConnection().getConnection();
//            executeSQLFromFileName(conn, ddlFileReader, "DELETE");  // 2回やる必要は無い。
            executeSQLFromFileNameThrowException(conn, ddlFileReader, "DELETE");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (conn != null) {
                try {
                    conn.commit();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void executeDropSequenceWithoutCommit(DDLFileReader ddlFileReader) {
        Connection conn = null;
        try {
            conn = getDbConnection().getConnection();
            executeSQLFromFileName(conn, ddlFileReader, "DROP SEQUENCE");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
