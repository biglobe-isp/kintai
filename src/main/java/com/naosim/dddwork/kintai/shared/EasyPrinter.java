package com.naosim.dddwork.kintai.shared;

public interface EasyPrinter {

    default void println() {
        System.out.println();
    }

    default void println(String format, Object... args) {

        final String content = String.format(format, args);
        System.out.println(content);
    }
}
