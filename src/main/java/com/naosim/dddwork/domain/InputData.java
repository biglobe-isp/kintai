package com.naosim.dddwork.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
public class InputData {

    @AllArgsConstructor @Getter
    public enum MethodType {
        INPUT("input"),
        TOTAL("total");

        private final String methodTypeStr;
    }

    @Getter
    private final MethodType methodType;

    @Getter
    private String date;

    @Getter
    private String startTime;

    @Getter
    private String endTime;

    @Getter
    private int startH;

    @Getter
    private int startM;

    @Getter
    private int endH;

    @Getter
    private int endM;

    @Getter
    private String now;

    @Getter String yearMonth;


    public InputData(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        String methodTypeStr = args[0];
        this.methodType = this.getMethodTypeFromString(methodTypeStr);

        switch (this.methodType) {
            case INPUT:
                this.setFieldsWhenInputProcess(args);
                break;
            case TOTAL:
                this.setFieldsWhenTotalProcess(args);
                break;
            default:
        }
    }

    private void setFieldsWhenInputProcess(String[] args) {
        if(args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }
        this.date = args[1];
        this.startTime = args[2];
        this.endTime = args[3];
        this.now = LocalDateTime.now().toString();

        this.startH = Integer.valueOf(this.startTime.substring(0, 2));
        this.startM = Integer.valueOf(this.startTime.substring(2, 4));

        this.endH = Integer.valueOf(this.endTime.substring(0, 2));
        this.endM = Integer.valueOf(this.endTime.substring(2, 4));
    }

    private void setFieldsWhenTotalProcess(String[] args) {
        if(args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }
        this.yearMonth = args[1];
    }

    private MethodType getMethodTypeFromString(String methodTypeStr) {
        if (MethodType.INPUT.methodTypeStr.equals(methodTypeStr)) return MethodType.INPUT;
        if (MethodType.TOTAL.methodTypeStr.equals(methodTypeStr)) return MethodType.TOTAL;
        throw new RuntimeException("methodTypeが不正です");
    }
}
