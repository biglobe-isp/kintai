package com.naosim.dddwork.kintai.api.feature.input;

import com.naosim.dddwork.kintai.api.pod.AttendanceDatePod;
import com.naosim.dddwork.kintai.api.pod.BeginTimePod;
import com.naosim.dddwork.kintai.api.pod.EndTimePod;
import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.api.request.protocol.RequestOperandVerifiable;
import com.naosim.dddwork.kintai.service.command.DailyWorkTimeRegistration;
import lombok.ToString;

import java.util.Arrays;

import static com.naosim.dddwork.kintai.api.feature.input.InputRequestOperandParser.Regulation.Operands.AttendanceDate;
import static com.naosim.dddwork.kintai.api.feature.input.InputRequestOperandParser.Regulation.Operands.BeginTime;
import static com.naosim.dddwork.kintai.api.feature.input.InputRequestOperandParser.Regulation.Operands.EndTime;


/**
 * ［指定日の勤怠登録］オペランド解析機
 */
public class InputRequestOperandParser {

    @ToString
    static class Regulation implements RequestOperandVerifiable {

        @ToString
        enum Operands {

            AttendanceDate(0),
            BeginTime(1),
            EndTime(2),
            ;

            private int index;

            Operands(int index) {
                this.index = index;
            }
        }

        @Override
        public int numberOfRequired() {
            return Operands.values().length;
        }
    }

    static Regulation regulation = new Regulation();

    private final AttendanceDatePod _attendanceDatePod;
    private final BeginTimePod _beginTimePod;
    private final EndTimePod _endTimePod;


    public static InputRequestOperandParser of(RequestOperands operands) {
        return new InputRequestOperandParser(operands);
    }

    public InputRequestOperandParser(RequestOperands operands) {

        if (operands.isNotSufficientFor(regulation)) {
            throw new IllegalArgumentException(
                    String.format("[INPUT]リクエストのオペランドが不足しています．[必要数=%d, 必要項目=%s, 指定オペランド=%s]",
                            regulation.numberOfRequired(),
                            Arrays.toString(Regulation.Operands.values()),
                            operands));
        }

        _attendanceDatePod = new AttendanceDatePod(operands.at(AttendanceDate.index));
        _beginTimePod = new BeginTimePod(operands.at(BeginTime.index));
        _endTimePod = new EndTimePod(operands.at(EndTime.index));
    }


    public DailyWorkTimeRegistration.Parameter serviceParameter() {

        return DailyWorkTimeRegistration.Parameter.of(
                _attendanceDatePod.domainObject(),
                _beginTimePod.domainObject(),
                _endTimePod.domainObject()
        );
    }
}
