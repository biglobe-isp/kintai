package jp.co.esumit.kintai.api;

import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime;
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime;

import java.util.Arrays;

public class Adaptor {
    public TargetDay toTargetDay(String inputArgs[]) {

        return new TargetDay(Arrays.stream(inputArgs)
                                     .filter(x -> x.startsWith(Prefix.DATE))
                                     .findFirst()
                                     .orElseThrow(() -> new RuntimeException("対象日を" + Prefix.DATE + "YYYYMMDDで入力してください。"))
                                     .replace(Prefix.DATE, ""));
    }

    public StartTime toStartTime(String inputArgs[]) {

        return new StartTime(Arrays.stream(inputArgs)
                                     .filter(x -> x.startsWith(Prefix.START))
                                     .findFirst()
                                     .orElseThrow(() -> new RuntimeException("開始時刻を" + Prefix.START + "hh_mmで入力してください。"))
                                     .replace(Prefix.START, "")
                                     .replace("_", ""));
    }

    public EndTime toEndTime(String inputArgs[]) {

        return new EndTime(Arrays.stream(inputArgs)
                                   .filter(x -> x.startsWith(Prefix.END))
                                   .findFirst()
                                   .orElseThrow(() -> new RuntimeException("終了時刻を" + Prefix.END + "hh_mmで入力してください。"))
                                   .replace(Prefix.END, "")
                                   .replace("_", ""));
    }
}
