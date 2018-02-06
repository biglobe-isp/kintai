package jp.co.biglobe.test.util.dbunit;

import jp.co.biglobe.test.util.dbunit.xml.Fixture;
import org.dbunit.AbstractDatabaseTester;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DBにテストデータを格納したり、取得する
 */
public class TestDataMaker extends AbstractDatabaseTester {

    private final DbConnector dbConnector;

    public TestDataMaker(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public IDatabaseConnection getConnection() throws Exception {
        return this.dbConnector.getDbConnection();
    }

    /**
     * Map型のデータをデータベースに格納する。
     * @param sqlByXml
     */
    public void cleanInsertQuery(Map sqlByXml) {

        InputSource is = new InputSource(new StringReader(Fixture.convertXmlFormat(sqlByXml)));
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);

        try {
            ReplacementDataSet replacementDataSet = new ReplacementDataSet(builder.build(is));
            replacementDataSet.addReplacementObject("null", null);
            setDataSet(replacementDataSet);
            setSetUpOperation(DatabaseOperation.INSERT);
            onSetup();
            closeConnection(dbConnector.getDbConnection());
        } catch (DataSetException e) {
            throw new RuntimeException(e.getMessage() + e.getCause() + e.getStackTrace());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage() + e.getSQLState() + e.getCause());
        } catch (DatabaseUnitException e) {
            throw new RuntimeException(e.getMessage() + e.getCause() + e.getStackTrace());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + e.getCause() + e.getStackTrace());
        }
    }

    /**
     * 期待値のテーブルデータを取得する
     *
     * @param expectedFileName 期待値のテーブルデータが定義されたファイルのパス
     * @param tableName        テーブル名
     * @param sortColumns      ソート条件のカラム名
     * @return
     */
    public ITable getExpectedTable(String expectedFileName, String tableName, String[] sortColumns) throws Exception {
        Timestamp timestamp = Convert.convertDate2TimeStamp(new Date());

        InputStream expectedIn = getClass().getResourceAsStream(expectedFileName);
        ReplacementDataSet expectedDataSetReplacement = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(expectedIn));
        expectedDataSetReplacement.addReplacementObject("[null]", null);
//        expectedDataSetReplacement.addReplacementObject("[yyyyMM]", new DateTime().toString("yyyyMM"));
//        expectedDataSetReplacement.addReplacementObject("[yyyyMM-1]", new DateTime().plusMonths(-1).toString("yyyyMM"));
//        expectedDataSetReplacement.addReplacementObject("[yyyyMMDD]", new DateTime().toString("yyyyMMdd"));
//        expectedDataSetReplacement.addReplacementObject("[yyyyMMDD-1]", new DateTime().plusDays(-1).toString("yyyyMMdd"));
//        expectedDataSetReplacement.addReplacementObject("[yyyy-MM-DD]", new DateTime().toString("yyyy-MM-dd"));
//        expectedDataSetReplacement.addReplacementObject("[timestamp]", new DateTime().toString("yyyy-MM-dd 00:00:00"));

        try {
            expectedIn.close();
        } catch (IOException ignore) {
        }

        ITable expected = expectedDataSetReplacement.getTable(tableName);
        return new SortedTable(expected, sortColumns);
    }

    /**
     * 指定したテーブルのテーブルデータを取り出す
     *
     * @param tableName   テーブル名
     * @param sortColumns ソート条件のカラム名
     * @return
     */
    public ITable getActualTable(String tableName, String[] sortColumns) throws Exception {
        ITable actual = getConnection().createDataSet().getTable(tableName);
        return new SortedTable(actual, sortColumns);
    }


    /**
     * 指定したテーブルのテーブルデータを取り出し、指定した項目のMapを作成する
     *
     * @param tableName
     * @param sortColumns
     * @param columns
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> getActualTable(String tableName, String[] sortColumns, String[] columns) throws Exception {
        ITable iTable = getActualTable(tableName, sortColumns);
        return generateITableToMaps(columns, iTable);
    }

    /**
     * 期待値のテーブルデータを取得し、指定した項目のMapの配列を生成する
     *
     * @param expectedFileName
     * @param tableName
     * @param sortColumns
     * @param columns
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> getExpectedTable(String expectedFileName, String tableName, String[] sortColumns, String[] columns) throws Exception {
        ITable iTable = getExpectedTable(expectedFileName, tableName, sortColumns);
        return generateITableToMaps(columns, iTable);
    }

    /**
     * ITableをMapの配列に変換する
     *
     * @param columns
     * @param iTable
     * @return
     * @throws DataSetException
     */
    private List<Map<String, String>> generateITableToMaps(String[] columns, ITable iTable) throws DataSetException {
        List<Map<String, String>> list = new LinkedList<>();
        for (int i = 0; i < iTable.getRowCount(); i++) {
            Map<String, String> map = new HashMap<>();
            list.add(map);
            for (String column : columns) {
                map.put(column, iTable.getValue(i, column) + "");
            }
        }
        return list;
    }

    static class Convert {

        static private final String DATE_PATTERN = "yyyy-MM-dd hh:mm:ss";

        public static String convertDate2String(Date date) {
            return (new SimpleDateFormat(DATE_PATTERN)).format(date);
        }

        public static Timestamp convertDate2TimeStamp(Date date) {
            return Timestamp.valueOf(convertDate2String(date));
        }

    }

}
