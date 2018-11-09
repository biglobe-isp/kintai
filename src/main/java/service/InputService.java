package service;

import datasource.InputCsvDatasource;
import domain.EndDomain;
import domain.StartDomain;

public class InputService {
    public InputService(String[] inputData) {
        if (inputData.length < 4) {
            throw new RuntimeException("引数が足りません");
        }

        StartDomain startDomain = new StartDomain(inputData[2]);
        EndDomain endDomain = new EndDomain(inputData[3]);

        int workMinutes = endDomain.endH * 60 + endDomain.endM - (startDomain.startH * 60 + startDomain.startM);

        if (endDomain.endH == 12) {
            workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 13) {
            workMinutes -= 60;
        }

        if (endDomain.endH == 18) {
            workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 19) {
            workMinutes -= 60;
        }

        if (endDomain.endH == 21) {
            workMinutes -= endDomain.endM;
        } else if (endDomain.endH >= 22) {
            workMinutes -= 60;
        }

        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);

        // CSV出力
        new InputCsvDatasource(inputData, workMinutes, overWorkMinutes);
    }
}
