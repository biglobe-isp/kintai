package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.WorkDataRepository;

/**
 * 実行機能
 */
public interface ExecuteFunction {
    String execute(InputInformation information);

    ExecutiveCommandType getExecutiveCommandType();

    void setExecuteFunction(WorkDataRepository repository);
}