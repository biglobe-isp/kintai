package com.naosim.dddwork.service;

import java.util.Arrays;

public class SelectExecuteFunction {
    private final ExecuteFunction[] functionArr = new ExecuteFunction[]{ new InputFunction(), new TotalFunction() };
    private final ExecuteFunctionInformation information = new ExecuteFunctionInformation();

    public String executeFunction(String[] commands) {
        prepareExecuteFunction(commands);

        for (ExecuteFunction function : functionArr) {
            if(function.getExecutiveCommandType() == information.executiveCommandType){
                return function.execute(information.argumentsArray);
            }
        }

        throw new IllegalArgumentException("関数を実行できませんでした");
    }

    public void prepareExecuteFunction(String[] commands) {
        setExecuteFunction(commands);
        extractFunctionArguments(commands);
    }

    private void setExecuteFunction(String[] commands) {
        if (commands.length < 1) throw new IllegalArgumentException("引数がありません。");

        try {
            information.executiveCommandType = convertStringToCommand(commands[0]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("入力された機能名が見つかりません。");
        }
    }

    private ExecutiveCommandType convertStringToCommand(String commandStr) throws IllegalArgumentException {
        return ExecutiveCommandType.valueOf(commandStr);
    }

    private void extractFunctionArguments(String[] commands) {
        information.argumentsArray = Arrays.copyOfRange(commands, 1,commands.length - 1);
    }


}