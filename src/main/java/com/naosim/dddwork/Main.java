package com.naosim.dddwork;

import com.naosim.dddwork.api.WorkTimeApi;

public class Main {
    public static void main(String[] args) {
        WorkTimeApi workTimeApi = new WorkTimeApi();
        workTimeApi.workTimeCalculate(args);
    }
}