package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.daily_work.*;
import com.naosim.dddwork.service.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 入力API
 */
public class InputCommand {
    public String input(String[] arguments) {
        InputInformation information = convertArgumentsToInputInformation(arguments);

        return executeFunction(information);
    }

    private InputInformation convertArgumentsToInputInformation(String[] commands) throws IllegalArgumentException {
        InputInformation inputInformation = new InputInformation();

        inputInformation.setExecutiveCommandType(
                convertToInputCommandType(commands[0])
        );

        setInputInformation(inputInformation, commands);

        return inputInformation;
    }

    private ExecutiveCommandType convertToInputCommandType(String commandStr) throws IllegalArgumentException {
        if(nullCheckCommandType(commandStr))
            throw new IllegalArgumentException("実行する機能タイプが入力されていません。");

        try {
            return ExecutiveCommandType.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("実行する機能を選択できません。");
        }
    }

    private boolean nullCheckCommandType(String commandStr) {
        return commandStr == null;
    }

    private void setInputInformation (InputInformation inputInformation, String[] commands) {
        switch (inputInformation.getExecutiveCommandType()) {
            case input:
                setInputInformationForInputCommand(inputInformation, commands);
                break;
            case total:
                setInputInformationForTotalCommand(inputInformation, commands);
                break;
            default:
                throw new IllegalArgumentException("実行機能に必要な情報設定が実装されていません。機能名: " + inputInformation.getExecutiveCommandType());
        }
    }

    private void setInputInformationForInputCommand (InputInformation inputInformation, String[] commands) {
        if(checkIsEnoughArguments(commands, 4)) throw new IllegalArgumentException("入力機能に必要な引数の数が一致していません。");

        inputInformation.setWorkDate(
                convertToWorkDate(commands[1])
        );
        inputInformation.setStartWorkTime(
                convertToStartWorkTime(commands[2])
        );
        inputInformation.setEndWorkTime(
                convertToEndWorkTime(commands[3])
        );
    }

    private void setInputInformationForTotalCommand (InputInformation inputInformation, String[] commands) {
        if(checkIsEnoughArguments(commands, 2)) throw new IllegalArgumentException("入力機能に必要な引数の数が一致していません。");

        inputInformation.setAggregationYearMonth(
                convertToAggregationYearMonth(commands[1])
        );
    }

    static boolean checkIsEnoughArguments(String[] commands, int requiredArguments) {
        return commands.length != requiredArguments;
    }

    private WorkDate convertToWorkDate(String command){
        if(validateWorkDate(command)) throw new IllegalArgumentException("勤務実施日が不正な値です。");

        int year = Integer.parseInt(command.substring(0, 4));
        int month = Integer.parseInt(command.substring(4, 6));
        int day = Integer.parseInt(command.substring(6, 8));
        return new WorkDate(LocalDate.of(year, month, day));
    }

    private AggregationYearMonth convertToAggregationYearMonth(String command){
        if(validateAggregationYearMonth(command)) throw new IllegalArgumentException("勤務実施日が不正な値です。");

        int year = Integer.parseInt(command.substring(0, 4));
        int month = Integer.parseInt(command.substring(4, 6));
        return new AggregationYearMonth(LocalDate.of(year, month, 1));
    }

    private boolean validateWorkDate(String command){
        return command == null || command.length() != 8;
    }

    private boolean validateAggregationYearMonth(String command){ return command == null || command.length() != 6; }

    private StartWorkTime convertToStartWorkTime(String command){
        if(validateWorkTime(command)) throw new IllegalArgumentException("勤務開始時刻が不正な値です。");
        return new StartWorkTime(parseWorkTime(command));
    }

    private EndWorkTime convertToEndWorkTime(String command){
        if(validateWorkTime(command)) throw new IllegalArgumentException("勤務終了時刻が不正な値です。");
        return new EndWorkTime(parseWorkTime(command));
    }

    private boolean validateWorkTime(String command){
        return command == null || command.length() != 4;
    }

    private LocalTime parseWorkTime(String command){
        int hour = Integer.parseInt(command.substring(0, 2));
        int minute = Integer.parseInt(command.substring(2, 4));
        return LocalTime.of(hour, minute);
    }

    private String executeFunction(InputInformation information) {
        switch (information.getExecutiveCommandType()) {
            case input:
                DailyWorkData inputOutput = InputFunction.execute(information);
                return "入力に成功しました。" + System.lineSeparator() +
                        "入力したデータ：" + inputOutput.getWorkDate() + ","
                        + inputOutput.getTotalWorkTimes();
            case total:
                AggregationResult totalOutput = TotalFunction.execute(information);
                return "勤務時間：" + totalOutput.normalWorkingMinutes().getValue().toMinutes() / 60 + "時間"
                        + totalOutput.normalWorkingMinutes().getValue().toMinutes() % 60 + "分"
                        + System.lineSeparator()
                        + "残業時間：" + totalOutput.overworkingMinutes().getValue().toMinutes() / 60 + "時間"
                        + totalOutput.overworkingMinutes().getValue().toMinutes() % 60 + "分";
            default:
                throw new IllegalArgumentException("関数を実行できませんでした");
        }
    }
}