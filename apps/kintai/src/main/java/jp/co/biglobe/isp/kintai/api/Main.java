package jp.co.biglobe.isp.kintai.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            switch (MethodType.of(args[0])) {
                // case INPUT ->
                // case TOTAL ->
                default -> throw new RuntimeException("引数のメソッドタイプは定義されていません。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
