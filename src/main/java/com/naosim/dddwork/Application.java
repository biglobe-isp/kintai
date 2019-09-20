package com.naosim.dddwork;

import com.naosim.dddwork.api.AttendanceAdministrationMain;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setLogStartupInfo(false);
        springApplication.run(args);
    }

    private AttendanceAdministrationMain attendanceAdministrationMain = new AttendanceAdministrationMain();

    @Override
    public void run(String... args) {
        try {
            System.out.println(attendanceAdministrationMain.command(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
