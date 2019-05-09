package com.naosim.dddwork.kintai;

import com.naosim.dddwork.kintai.api.ArgumentParser;
import com.naosim.dddwork.kintai.api.Request;
import com.naosim.dddwork.kintai.api.Usage;

public class App {

    public static void main(String[] args) {

        try {
            ArgumentParser parser = new ArgumentParser(args);
            Request request = parser.pickRequest();
            request.execute(args);
        }
        catch (Exception e) {
            e.printStackTrace();
            Usage.print();
        }
    }
}