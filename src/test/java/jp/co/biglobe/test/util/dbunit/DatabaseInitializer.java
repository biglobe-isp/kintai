package jp.co.biglobe.test.util.dbunit;

import jp.co.biglobe.test.util.dbunit.file.DDLFileReader;
import org.dbunit.DatabaseUnitException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * テスト開始前のデータベース初期化処理。
 * 必要なテーブルの作成などを行う。
 */
public class DatabaseInitializer {

    private final DbConnector dbConnector;
    private final DbUnitTesterProperty dbUnitTesterProperty;

    private DDLFileReader createTableDdlFileReader;
    private DDLFileReader createIndexDdlFileReader;
    private DDLFileReader createSequenceDdlFileReader;
    private DDLFileReader alterTableDdlFileReader;

    // 開発PC用
    private DDLFileReader createLocalTableDdlFileReader;
    private DDLFileReader createLocalIndexDdlFileReader;
    private DDLFileReader createLocalSequenceDdlFileReader;

    private String dbName;

    public DatabaseInitializer(DbConnector dbConnector, DbUnitTesterProperty dbUnitTesterProperty) {
        this.dbConnector = dbConnector;
        this.dbUnitTesterProperty = dbUnitTesterProperty;

        this.createTableDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getCreateTableDDLPath());
        this.createIndexDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getCreateIndexDDLPath());
        this.createSequenceDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getCreateSequenceDDLPath());
        this.alterTableDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getAlterTableDDLPath());
        this.createLocalTableDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getLocalCreateTableDDLPath());
        this.createLocalIndexDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getLocalCreateIndexDDLPath());
        this.createLocalSequenceDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getLocalCreateSequenceDDLPath());

        this.dbName = dbUnitTesterProperty.getDbName();
    }

    /**
     * テーブルなどのオブジェクトを作成する
     */
    public void createDbObject() {
        try {
            this.executeDDL();
        } catch (DatabaseUnitException e) {
            throw new AssertionError(e);
        } catch (SQLException e) {
            throw new AssertionError(e);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * DDL文を実行する
     *
     * @return CreateTableのDDL文
     * @throws DatabaseUnitException
     * @throws SQLException
     * @throws java.io.IOException
     */
    private void executeDDL() throws DatabaseUnitException, SQLException, IOException {
        // H2の場合プロシージャを作成する。
        // Oracleで実行されてもExceptionを握りつぶすため、実行はする
        DDLFileReader localAliesDdlFileReader = new DDLFileReader(dbUnitTesterProperty.getLocalAliasDDLPath());
        dbConnector.ddlExecute(localAliesDdlFileReader);

        //
        //dbConnector.executeQuery("SET REFERENTIAL_INTEGRITY FALSE");

        // プロジェクト固有でないテーブルを作成する
        dbConnector.executeDropTable(createLocalTableDdlFileReader);
        dbConnector.executeDropSequence(createLocalSequenceDdlFileReader);
        dbConnector.ddlExecute(createLocalTableDdlFileReader);
        dbConnector.ddlExecute(createLocalIndexDdlFileReader);
        dbConnector.ddlExecute(createLocalSequenceDdlFileReader);

        // 最初に書くオブジェクトをDropする。ただし、存在しない場合などExceptionが発生しても握りつぶします
        dbConnector.executeDropTable(createTableDdlFileReader);
        dbConnector.executeDropSequence(createSequenceDdlFileReader);

        // Create Table実行
        dbConnector.ddlExecuteForCreateTable(createTableDdlFileReader, dbName);

        // Alter Table実行
        dbConnector.ddlExecute(alterTableDdlFileReader);

        // Create Index実行
        dbConnector.ddlExecute(createIndexDdlFileReader);

        // Create Sequence実行
        dbConnector.ddlExecute(createSequenceDdlFileReader);
    }

    /**
     * 作成したテーブルをTruncateする
     */
    public void executeClearTableAndSeq() throws DatabaseUnitException, SQLException, IOException {

        // テーブルの参照制約削除→トランケート→テーブルの参照制約復活
        dbConnector.executeQuery("call fk_disabled()");
        dbConnector.executeQuery("SET REFERENTIAL_INTEGRITY FALSE"); // H2の場合は、truncateするためには、以下を実施する必要あり。

//      Truncate Table よりも、Delete の方が速い
//        dbConnector.executeTruncateTableWithoutCommit(createTableDdlFileReader);
//        dbConnector.executeTruncateTableWithoutCommit(createLocalTableDdlFileReader);
        dbConnector.executeDeleteTable(createTableDdlFileReader);
        dbConnector.executeDeleteTable(createLocalTableDdlFileReader);

        dbConnector.executeQuery("SET REFERENTIAL_INTEGRITY TRUE"); // 上でfalseにしているので、参照制約が無効になっているので、元に戻す
        dbConnector.executeQuery("call fk_enabled()");

        // Sequenceを作り直す
        dbConnector.executeDropSequenceWithoutCommit(createSequenceDdlFileReader);
        dbConnector.ddlExecuteWithoutCommit(createSequenceDdlFileReader);
        dbConnector.executeDropSequenceWithoutCommit(createLocalSequenceDdlFileReader);
        dbConnector.ddlExecuteWithoutCommit(createLocalSequenceDdlFileReader);
    }

}
