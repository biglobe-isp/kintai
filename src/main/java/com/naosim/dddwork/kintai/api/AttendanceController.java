package com.naosim.dddwork.kintai.api;

import com.naosim.dddwork.kintai.api.request.Request;
import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.datasource.settings.DataSourceConfiguration;
import com.naosim.dddwork.kintai.datasource.settings.option.DataStorePolicy;
import com.naosim.dddwork.kintai.settings.Environment;
import com.naosim.dddwork.kintai.shared.exception.ValidationException;


/**
 * 勤怠コントローラー
 */
public class AttendanceController {

    final String[] args;

    public AttendanceController(String[] args) {
        this.args = args;
    }


    public void execute() {

//TODO: 改善する

        DataSourceConfiguration.setup(DataStorePolicy.CSV, Environment.DATA_STORE_CSV_FILE_NAME);

        ArgumentParser parser = new ArgumentParser(args);
        Request request = parser.pickRequest();
        RequestOperands operands = parser.pickOperands();

        try {
            request.execute(operands);
        }
        catch (ValidationException e) {
            e.getErrorMessages().stream().
                    forEach(System.out::println);
            e.printStackTrace();
        }
    }
}
