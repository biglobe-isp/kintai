package com.naosim.dddwork.service;

public interface ExecuteFunction {
    boolean checkIsEnoughArguments(String[] commands);

    String execute(String[] commands);

    ExecutiveCommandType getExecutiveCommandType();

    void setExecuteFunction();

    void convertArgumentsToCorrectInputs(String[] commands);
}