package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.FileWriter;
import java.io.IOException;

@EqualsAndHashCode(callSuper = false)
@ToString
public class RegistData extends ProcessData {

    @Getter
    private int startH;

    @Getter
    private int startM;

    @Getter
    private int endH;

    @Getter
    private int endM;

    public RegistData(InputData inputData) {
        super(inputData);
        if (InputData.MethodType.INPUT.equals(inputData.getMethodType()))
            this.setFieldsWhenInputProcess(inputData);
    }

    @Override
    public void execute() throws IOException {
        int workMinutes = this.getEndH() * 60 + this.getEndM()
                - (this.getStartH() * 60 + this.getStartM());

        if(this.getEndH() == 12) {
            workMinutes -= this.getEndM();
        } else if(this.getEndH() >= 13) {
            workMinutes -= 60;
        }

        if(this.getEndH() == 18) {
            workMinutes -= this.getEndM();
        } else if(this.getEndH() >= 19) {
            workMinutes -= 60;
        }

        if(this.getEndH() == 21) {
            workMinutes -= this.getEndM();
        } else if(this.getEndH() >= 22) {
            workMinutes -= 60;
        }

        int overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
        try(FileWriter filewriter = new FileWriter(this.file, true)) {
            filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                    inputData.getDate(), inputData.getStartTime(), inputData.getEndTime(),
                    workMinutes, overWorkMinutes, inputData.getNow()));
        }

    }

    private void setFieldsWhenInputProcess(InputData inputData) {
        this.startH = Integer.valueOf(inputData.getStartTime().substring(0, 2));
        this.startM = Integer.valueOf(inputData.getStartTime().substring(2, 4));

        this.endH = Integer.valueOf(inputData.getEndTime().substring(0, 2));
        this.endM = Integer.valueOf(inputData.getEndTime().substring(2, 4));
    }

}
