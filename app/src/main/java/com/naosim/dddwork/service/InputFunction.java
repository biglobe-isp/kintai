package com.naosim.dddwork.service;

import com.naosim.dddwork.datasource.InputWorkDataToLocalCSV;
import com.naosim.dddwork.domain.InputWorkDataRepository;
import com.naosim.dddwork.domain.daily_work.ClockTime;
import com.naosim.dddwork.domain.daily_work.DailyWorkData;
import com.naosim.dddwork.domain.daily_work.WorkDate;

public class InputFunction implements ExecuteFunction {
    InputWorkDataRepository inputWorkDataRepository;
    DailyWorkData inputWorkData;

    @Override
    public boolean checkIsEnoughArguments(String[] commands) {
        return commands.length < 3;
    }

    @Override
    public String execute(String[] commands) {
        if(checkIsEnoughArguments(commands)) throw new IllegalArgumentException("入力に必要な引数が不足しています。");

        setExecuteFunction();
        convertArgumentsToCorrectInputs(commands);

        String output = inputWorkDataRepository.writeDailyWorkData(inputWorkData);
        return "入力に成功しました。¥n入力したデータ：" + output;
    }

    @Override
    public ExecutiveCommandType getExecutiveCommandType() {
        return ExecutiveCommandType.input;
    }

    @Override
    public void setExecuteFunction() {
        inputWorkDataRepository = new InputWorkDataToLocalCSV();
    }

    @Override
    public void convertArgumentsToCorrectInputs(String[] commands) {
        WorkDate workDate = convertToWorkDate(commands[0]);
        ClockTime startWorkTime = convertToClockTime(commands[1]);
        ClockTime endWorkTime = convertToClockTime(commands[2]);

        inputWorkData = new DailyWorkData(workDate, startWorkTime, endWorkTime);
    }

    private WorkDate convertToWorkDate(String command){
        if(validateWorkDate(command)) throw new IllegalArgumentException("勤務実施日が不正な値です。");

        int year = Integer.parseInt(command.substring(0, 4));
        int month = Integer.parseInt(command.substring(4, 6));
        int day = Integer.parseInt(command.substring(6, 8));
        return new WorkDate(year, month, day);
    }

    private boolean validateWorkDate(String command){
        return command == null || command.length() < 8;
    }

    private ClockTime convertToClockTime(String command){
        if(validateClockTime(command)) throw new IllegalArgumentException("勤務開始時刻または勤務終了時刻が不正な値です。");

        int hour = Integer.parseInt(command.substring(0, 2));
        int minute = Integer.parseInt(command.substring(2, 4));
        return new ClockTime(hour, minute);
    }

    private boolean validateClockTime(String command){
        return command == null || command.length() < 4;
    }
}