package com.naosim.dddwork.api;

import com.naosim.dddwork.service.SelectExecuteFunction;

public class InputCommand {
    public String input(String[] arguments) {
        SelectExecuteFunction selectExecuteFunction = new SelectExecuteFunction();
        return selectExecuteFunction.executeFunction(arguments);
    }
}