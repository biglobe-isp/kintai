package com.naosim.dddwork.api.attendance;

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

            MethodType methodType = new MethodType(args);

            if (methodType.isInput()) {

                System.out.println("isInput");

                attendanceInputService.input(args);

            } else if (methodType.isTotal()) {

                System.out.println("isTotal");

                attendanceTotalService.refer(args);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
