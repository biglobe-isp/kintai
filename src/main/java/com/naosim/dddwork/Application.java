package com.naosim.dddwork;

import com.naosim.dddwork.api.AttendanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.setLogStartupInfo(false);
        springApplication.run(args);
    }

    private final AttendanceController attendanceController;

    @Autowired
    public Application(AttendanceController attendanceController) {
        this.attendanceController = attendanceController;
    }

    @Override
    public void run(String... args) {
        // Main.main(args);
        try {
            System.out.println(attendanceController.command(args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
