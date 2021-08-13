package jp.co.esumit.kintai.api;

import jp.co.esumit.kintai.datasource.AppConst;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;

import java.util.Arrays;

public class Adaptor {

    public TargetDay toTargetDay(String inputArgs[]){

     return new TargetDay(Arrays.stream(inputArgs)
                      .filter(x -> x.startsWith(AppConst.Prefix.DATE))
                      .findFirst()
                      .orElseThrow(() -> new RuntimeException("対象日を"+AppConst.Prefix.DATE+"YYYYMMDDで入力してください。"))
                      .replace(AppConst.Prefix.DATE,""));
    }

    public StartTime toStartTime(String inputArgs[]){

        return new StartTime(Arrays.stream(inputArgs)
                     .filter(x -> x.startsWith(AppConst.Prefix.START))
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("開始時刻を"+AppConst.Prefix.START+"hh_mmで入力してください。"))
                     .replace(AppConst.Prefix.START,"")
                     .replace("_",""));
    }

    public EndTime toEndTime(String inputArgs[]){

        return new EndTime(Arrays.stream(inputArgs)
                     .filter(x -> x.startsWith(AppConst.Prefix.END))
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("終了時刻を"+AppConst.Prefix.END+"hh_mmで入力してください。"))
                     .replace(AppConst.Prefix.END,"")
                     .replace("_",""));
    }

}
