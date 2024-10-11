package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.LocalCSVWorkData;
import com.naosim.dddwork.domain.WorkDataRepository;

/**
 * 勤怠入力機能
 */
public class InputFunction implements ExecuteFunction {
    WorkDataRepository inputWorkDataRepository;

    @Override
    public String execute(InputInformation information) {

        setExecuteFunction(new LocalCSVWorkData());

        String output = inputWorkDataRepository.writeDailyWorkData(
                information.getWorkDate(),
                information.getStartWorkTime(),
                information.getEndWorkTime());
        return "入力に成功しました。" + System.lineSeparator() + "入力したデータ：" + output;
    }

    @Override
    public ExecutiveCommandType getExecutiveCommandType() {
        return ExecutiveCommandType.input;
    }

    @Override
    public void setExecuteFunction(WorkDataRepository repository) {
        inputWorkDataRepository = repository;
    }
}