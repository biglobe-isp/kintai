package com.naosim.dddwork.service;

/**
 * 実行機能選択
 */
public class SelectExecuteFunction {
    private final ExecuteFunction[] functionArr = new ExecuteFunction[]{ new InputFunction(), new TotalFunction() };

    public String executeFunction(InputInformation information) {
        for (ExecuteFunction function : functionArr) {
            if(function.getExecutiveCommandType() == information.getExecutiveCommandType()){
                return function.execute(information);
            }
        }

        throw new IllegalArgumentException("関数を実行できませんでした");
    }
}