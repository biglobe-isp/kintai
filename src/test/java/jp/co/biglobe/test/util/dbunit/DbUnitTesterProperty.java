/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.co.biglobe.test.util.dbunit;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbUnitTesterProperty {

    @Getter
    @Value("${jdbc.dbName}")
    private String dbName;

    @Getter
    @Value("${jdbc.ddl.createTablePath}")
    private String createTableDDLPath;

    @Getter
    @Value("${jdbc.ddl.createIndexPath}")
    private String createIndexDDLPath;

    @Getter
    @Value("${jdbc.ddl.createSequencePath}")
    private String createSequenceDDLPath;

    @Getter
    @Value("${jdbc.ddl.alterTablePath}")
    private String alterTableDDLPath;

    @Getter
    @Value("${jdbc.ddl.tableSpaceDDLPath}")
    private String tableSpaceDDLPath;

    @Getter
    @Value("${jdbc.ddl.localAliasPath}")
    private String localAliasDDLPath;

    @Getter
    @Value("${jdbc.ddl.localCreateTablePath}")
    private String localCreateTableDDLPath;

    @Getter
    @Value("${jdbc.ddl.localCreateSequencePath}")
    private String localCreateSequenceDDLPath;

    @Getter
    @Value("${jdbc.ddl.localCreateIndexPath}")
    private String localCreateIndexDDLPath;
}
