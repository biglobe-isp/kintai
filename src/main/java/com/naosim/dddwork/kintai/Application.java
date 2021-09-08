package com.naosim.dddwork.kintai;

import com.naosim.dddwork.kintai.config.ApplicationConfiguration;
import com.naosim.dddwork.kintai.controller.AttendanceHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        ApplicationConfiguration.class
})
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args)) {
            AttendanceHandler app = ctx.getBean(AttendanceHandler.class);
            app.call(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
