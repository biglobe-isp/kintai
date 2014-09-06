package jp.co.biglobe.test.util.dbunit.xml;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixtureChangeTarget {

    public final String tableName;
    public final int count;
    public final String columnName;

}
