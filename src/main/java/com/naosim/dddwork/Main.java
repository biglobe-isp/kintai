package com.naosim.dddwork;

import com.naosim.dddwork.service.KintaiKanriService;

public class Main {

    public static void main(String[] args) {
        try {
            KintaiKanriService service = new KintaiKanriService();
            service.execute(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}