package jp.co.biglobe.isp.kintai.api;

import com.google.common.base.Predicates;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum InputArgsPrefix {
    /**
     * 勤怠日付.
     */
    DATE("-date:"),
    /**
     * 勤怠開始時刻.
     */
    START("-start:"),
    /**
     * 勤怠終了時刻.
     */
    END("-end:");
    private final String value;

    InputArgsPrefix(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean containsPrefix(String str) {
        return str.startsWith(value);
    }
}
