package com.naosim.dddwork.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        com.naosim.dddwork.Main oldMain = new com.naosim.dddwork.Main();
        oldMain.main(args);
        System.out.println("finished kintai registration!");
    }
}
