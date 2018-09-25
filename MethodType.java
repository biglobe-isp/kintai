package com.naosim.dddwork;

public class MethodType {

    private String value;

    MethodType(String[] args) {

        System.out.println("MethodType");

        if (args == null || args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        this.value = args[0];

        System.out.println("args[0]:" + this.value);

        if (this.isInput()) {
            if (args.length < 4) {
                throw new RuntimeException("引数が足りません");
            }
        } else if (this.isTotal()) {
            if (args.length < 2) {
                throw new RuntimeException("引数が足りません");
            }
        }
    }

    boolean isInput() {
        return "input".equals(this.value);
    }

    boolean isTotal() {
        return "total".equals(this.value);
    }
}
