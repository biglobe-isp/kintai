package com.naosim.dddwork.kintai.api.feature.total;

import com.naosim.dddwork.kintai.api.pod.AttendanceMonthPod;
import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.api.request.protocol.RequestOperandVerifiable;
import com.naosim.dddwork.kintai.service.query.MonthlyTotalWorkedTimeQueryService;

import java.util.Arrays;

import static com.naosim.dddwork.kintai.api.feature.total.TotalRequestOperandParser.Regulation.Operands.AttendanceMonth;


/**
 * ［指定月の勤務時間合計表示］オペランド解析機
 */
public class TotalRequestOperandParser {

    static class Regulation implements RequestOperandVerifiable {

        enum Operands {

            AttendanceMonth(0)
            ;

            private int index;

            Operands(int index) {
                this.index = index;
            }
        }

        @Override
        public int numberOfRequired() {
            return TotalRequestOperandParser.Regulation.Operands.values().length;
        }
    }

    static TotalRequestOperandParser.Regulation regulation = new TotalRequestOperandParser.Regulation();

    final AttendanceMonthPod _attendanceMonthPod;


    public static TotalRequestOperandParser of(RequestOperands operands) {
        return new TotalRequestOperandParser(operands);
    }

    public TotalRequestOperandParser(RequestOperands operands) {

        if (operands.isNotSufficientFor(regulation)) {
            throw new IllegalArgumentException(
            String.format("[TOTAL]リクエストのオペランドが不足しています．[必要数=%d, 必要項目=%s]", regulation.numberOfRequired(), Arrays.toString(Regulation.Operands.values())));
        }

        _attendanceMonthPod = new AttendanceMonthPod(operands.at(AttendanceMonth.index));
    }


    public MonthlyTotalWorkedTimeQueryService.Parameter serviceParameter() {

        return MonthlyTotalWorkedTimeQueryService.Parameter.of(
                _attendanceMonthPod.domainObject()
        );
    }
}
