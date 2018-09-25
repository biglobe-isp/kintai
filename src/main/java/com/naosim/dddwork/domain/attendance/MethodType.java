package com.naosim.dddwork.domain.attendance;

public class MethodType {

    private String value;

    public MethodType(String[] args) {

        if (args == null || args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }

        this.value = args[0];

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

    public boolean isInput() {
        return "input".equals(this.value);
    }

    public boolean isTotal() {
        return "total".equals(this.value);
    }
}
