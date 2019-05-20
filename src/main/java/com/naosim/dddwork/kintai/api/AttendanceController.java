package com.naosim.dddwork.kintai.api;

import com.naosim.dddwork.kintai.api.request.Request;
import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.api.settings.DataStorePolicy;
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
        ArgumentParser parser = new ArgumentParser(args);
        Request request = parser.pickRequest();
        RequestOperands operands = parser.pickOperands();

        try {
            request.execute(operands, DataStorePolicy.CSV);
        }
        catch (ValidationException e) {
            e.getErrorMessages().stream().
                    forEach(System.out::println);
            e.printStackTrace();
        }
    }
}
