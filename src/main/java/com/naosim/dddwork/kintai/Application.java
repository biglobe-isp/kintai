package com.naosim.dddwork.kintai;

import com.naosim.dddwork.kintai.config.ApplicationConfiguration;
import com.naosim.dddwork.kintai.controller.AttendanceServiceHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.naosim.dddwork"})
@Import({
        ApplicationConfiguration.class
})
public class Application {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args)) {
            AttendanceServiceHandler app = ctx.getBean(AttendanceServiceHandler.class);
            app.call(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
