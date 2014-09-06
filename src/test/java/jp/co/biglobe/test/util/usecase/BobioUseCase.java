/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.biglobe.test.util.usecase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 */
public class BobioUseCase {
   
    private String SCENARIO_NAME;    
    private String BOBIO_SNR_BASE = System.getProperty("user.dir") + "/src/test/resources/scenario/";
    
    public BobioUseCase(String SCENARIO_NAME){
            this.SCENARIO_NAME = SCENARIO_NAME;
    }

    /**
     * UCに該当するファイルを設置する
     *  @param usecase
     **/
    public void set(String usecase) throws IOException {

        //UCの結果ファイル
        String UseCaseFile = BOBIO_SNR_BASE + SCENARIO_NAME + "/" + SCENARIO_NAME + "_" + usecase;
        FileChannel UseCaseChannel = getInputChannel(UseCaseFile);

        //実行される結果ファイル
        String CallFile = BOBIO_SNR_BASE + SCENARIO_NAME + "/" + SCENARIO_NAME;
        FileChannel callChannel = getOutputChannel(CallFile);
             
        //ファイルの置き換え(UCファイル⇒実行ファイル)
        try {
            UseCaseChannel.transferTo(0, UseCaseChannel.size(), callChannel);
        } finally {
            UseCaseChannel.close();
            callChannel.close();
        }        
    }
    
    public void unset() throws IOException {

        set("DEFAULT");

    }


    /**
     * 入力先ファイルのチャネルを取得する
     **/
    private FileChannel getInputChannel(String TargetFile) throws FileNotFoundException {
        FileInputStream UseCaseStream = new FileInputStream(TargetFile);
        return UseCaseStream.getChannel();
    }

    /**
     * 出力先ファイルのチャネルを取得する
     **/
    private FileChannel getOutputChannel(String TargetFile) throws FileNotFoundException {
        FileOutputStream UseCaseStream = new FileOutputStream(TargetFile);
        return UseCaseStream.getChannel();
    }    
  
}
