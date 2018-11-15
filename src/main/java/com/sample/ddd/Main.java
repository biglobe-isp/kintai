package com.sample.ddd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello World" +" "+ args[0]);

        //ファイルに書き込む
        File file = new File("test.txt");
        try {
            FileWriter fw = new FileWriter(file);     //ファイルを開く(対象ファイルがなければ新規作成)
            fw.write("ファイルテスト");  //()無いの内容をファイルに書き込む
            fw.close();     //ファイルを閉じる
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //ファイルから読み込む
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = br.readLine();    //一行読込み
            while (line != null) {
                System.out.println(line);   //表示
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //掛け算をする
    public static int run(int a) {
        return a * 2;
    }
}