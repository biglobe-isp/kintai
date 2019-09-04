package com.naosim.dddwork;

import com.naosim.dddwork.api.AttendanceRecordMain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setLogStartupInfo(false);
        springApplication.run(args);
    }

    @Autowired
    AttendanceRecordMain attendanceRecordMain;

    @Override
    public void run(String... args) {
        try {
            System.out.println(attendanceRecordMain.command(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
