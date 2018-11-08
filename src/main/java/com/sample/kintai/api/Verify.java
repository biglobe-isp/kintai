package com.sample.kintai.api;

public class Verify {
    public void inputCheck(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }
        String methodType = args[0];
        if ("input".equals(methodType)) {
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }
        } else if ("total".equals(methodType)) {
            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
        }
    }
}
