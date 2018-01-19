package com.naosim.dddwork;

import com.naosim.dddwork.service.WorkTimeService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        WorkTimeService workTimeService = new WorkTimeService();
        workTimeService.workTimeCalculate(args);
    }
}