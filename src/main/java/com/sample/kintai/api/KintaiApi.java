package com.sample.kintai.api;

import com.sample.kintai.datasource.CsvFileReader;
import com.sample.kintai.datasource.CsvFileWriter;
import com.sample.kintai.domain.CsvFileReaderInterface;
import com.sample.kintai.domain.CsvFileWriterInterface;
import com.sample.kintai.service.NyuryokuService;
import com.sample.kintai.service.SyukeiService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class KintaiApi {
    public static void main(String[] args) {
        Verify verify = new Verify();
        verify.inputCheck(args);
        try {
            String methodType = args[0];
            if ("input".equals(methodType)) {
                CsvFileWriterInterface ds = new CsvFileWriter();
                NyuryokuService service = new NyuryokuService();
                service.nyuryoku(args, ds);
            } else if ("total".equals(methodType)) {
                File file = new File("data.csv");
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                CsvFileReaderInterface ds = new CsvFileReader(fr,br);
                String yearMonth = args[1];
                SyukeiService service = new SyukeiService();
                service.syukei(yearMonth, ds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
