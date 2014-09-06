package jp.co.biglobe.test.util.dbunit.assertion;


import jp.co.biglobe.test.util.dbunit.xml.Fixture;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Map;

import static jp.co.biglobe.test.util.dbunit.assertion.ITableMatcher.tableOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DatabaseAssert {

    private final IDatabaseConnection iDatabaseConnection;

    public DatabaseAssert(IDatabaseConnection iDatabaseConnection) {
        this.iDatabaseConnection = iDatabaseConnection;
    }

    public void assertTableNoCount(Map expectedData, String tableName) throws Exception{

        ITable actual = getActualTableForNoCount(tableName);
        ITable expected = getExpectedTableForNoCount(expectedData, tableName);
        assertThat(actual, is(tableOf(expected)));
    }

    public void assertTableWithAllColumns(Map expectedData, String tableName, String[] sortColumns) throws Exception{

        ITable actual = getActualTable(tableName, sortColumns);
        ITable expected = getExpectedTableTemp(expectedData, tableName, sortColumns);
        assertThat(actual, is(tableOf(expected)));
    }

    public void assertTableWithExcludeColumns(Map expectedData, String tableName, String[] sortColumns, String[] excludeColumns) throws Exception{

        ITable actual = getActualTable(tableName, sortColumns);
        ITable actualExclude = DefaultColumnFilter.excludedColumnsTable(actual, excludeColumns);
        ITable expected = getExpectedTableTemp(expectedData, tableName, sortColumns);
        ITable expectedExclude = DefaultColumnFilter.excludedColumnsTable(expected, excludeColumns);
        assertThat(actualExclude, is(tableOf(expectedExclude)));
    }

    public ITable getActualTableForNoCount(String tableName) throws Exception {
        ITable actual = iDatabaseConnection.createDataSet().getTable(tableName);
        iDatabaseConnection.close();
        return actual;
    }

    public ITable getActualTable(String tableName, String[] sortColumns) throws Exception {
        ITable actual = iDatabaseConnection.createDataSet().getTable(tableName);
        iDatabaseConnection.close();
        return new SortedTable(actual, sortColumns);
    }

    public ITable getExpectedTableForNoCount(Map expectedData, String tableName) throws Exception {

        InputSource is = new InputSource(new StringReader(Fixture.convertXmlFormat(expectedData)));
        ReplacementDataSet expectedDataSetReplacement = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(is));

        ITable expected =  expectedDataSetReplacement.getTable(tableName);
        return expected;
    }

    public ITable getExpectedTableTemp(Map expectedData, String tableName, String[] sortColumns) throws Exception {

        InputSource is = new InputSource(new StringReader(Fixture.convertXmlFormat(expectedData)));
        ReplacementDataSet expectedDataSetReplacement = new ReplacementDataSet(new FlatXmlDataSetBuilder().build(is));
        expectedDataSetReplacement.addReplacementObject("null", null);
//        expectedDataSetReplacement.addReplacementObject("[yyyyMM]", new DateTime().toString("yyyyMM"));
//        expectedDataSetReplacement.addReplacementObject("[yyyyMM-1]", new DateTime().plusMonths(-1).toString("yyyyMM"));
//        expectedDataSetReplacement.addReplacementObject("[yyyyMMDD]", new DateTime().toString("yyyyMMdd"));
//        expectedDataSetReplacement.addReplacementObject("[yyyyMMDD-1]", new DateTime().plusDays(-1).toString("yyyyMMdd"));
//        expectedDataSetReplacement.addReplacementObject("[yyyy-MM-DD]", new DateTime().toString("yyyy-MM-dd"));
//        expectedDataSetReplacement.addReplacementObject("[timestamp]", new DateTime().toString("yyyy-MM-dd 00:00:00"));

        ITable expected =  expectedDataSetReplacement.getTable(tableName);
        return new SortedTable(expected, sortColumns);
    }
}
