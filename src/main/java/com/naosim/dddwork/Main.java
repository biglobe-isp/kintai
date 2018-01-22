package com.naosim.dddwork;

import com.naosim.dddwork.service.KintaiKanriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Main {

    @Autowired
    private KintaiKanriService kintaiKanriService;

    public static void main(String[] args) {
        try {
            ApplicationContext app = new ClassPathXmlApplicationContext("classpath:context.xml");
            Main main = app.getBean(Main.class);
            main.execute(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute(String[] args) throws IOException {
        this.kintaiKanriService.execute(args);
    }
}