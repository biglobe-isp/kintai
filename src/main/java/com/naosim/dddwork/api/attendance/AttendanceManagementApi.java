package com.naosim.dddwork.api.attendance;

import com.naosim.dddwork.api.form.EndTimeForm;
import com.naosim.dddwork.api.form.MethodTypeForm;
import com.naosim.dddwork.api.form.StartTimeForm;
import com.naosim.dddwork.api.form.TotalYearMonthForm;
import com.naosim.dddwork.api.form.WorkDateForm;
import com.naosim.dddwork.domain.attendance.MethodType;
import com.naosim.dddwork.service.attendance.AttendanceInputService;
import com.naosim.dddwork.service.attendance.AttendanceTotalService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 勤怠管理API
 */
@RestController
@NoArgsConstructor
public class AttendanceManagementApi {

    @Autowired
    private AttendanceInputService attendanceInputService;

    @Autowired
    private AttendanceTotalService attendanceTotalService;

    public void main(String[] args) {

        try {

            MethodType methodType = new MethodTypeForm(args).getValueObject();

            if (methodType.isInput()) {

                // 入力(input)
                attendanceInputService.input(
                        new AttendanceManagementInputRequest(
                                new WorkDateForm(args).getValueObject(),
                                new StartTimeForm(args).getValueObject(),
                                new EndTimeForm(args).getValueObject()
                        ).makeAttendanceInputApplication()
                );

            } else if (methodType.isTotal()) {

                // 集計(total)
                attendanceTotalService.refer(
                        new AttendanceManagementTotalRequest(
                                new TotalYearMonthForm(args).getValueObject()
                        ).makeAttendanceTotalInquiry()
                );

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
