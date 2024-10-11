package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.LocalCSVWorkData;
import com.naosim.dddwork.domain.WorkDataRepository;

/**
 * 勤怠集計機能
 */
public class TotalFunction implements ExecuteFunction {
    WorkDataRepository fetchWorkDataRepository;

    @Override
    public String execute(InputInformation information) {
        setExecuteFunction(new LocalCSVWorkData());

        return "現在未実装";
    }

    @Override
    public ExecutiveCommandType getExecutiveCommandType() {
        return ExecutiveCommandType.total;
    }

    @Override
    public void setExecuteFunction(WorkDataRepository repository) {
        // fetchWorkDataRepositoryを実装したCSVクラスを代入
        fetchWorkDataRepository = repository;
    }
}