package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.FetchWorkDataRepository;

public class TotalFunction implements ExecuteFunction {
    FetchWorkDataRepository fetchWorkDataRepository;

    @Override
    public boolean checkIsEnoughArguments(String[] commands) {
        return commands.length < 1;
    }

    @Override
    public String execute(String[] commands) {

        return "現在未実装";
    }

    @Override
    public ExecutiveCommandType getExecutiveCommandType() {
        return ExecutiveCommandType.total;
    }

    @Override
    public void setExecuteFunction() {
        // fetchWorkDataRepositoryを実装したCSVクラスを代入
    }

    @Override
    public void convertArgumentsToCorrectInputs(String[] commands) {

    }
}