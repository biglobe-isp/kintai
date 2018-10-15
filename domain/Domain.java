package domain;

import java.time.LocalDateTime;
import datasource.Datasource;

public class Domain {

    //日付（YYYYMMDD）
    private String date = null;
    //開始時刻（MMDD）
    private String start = null;
    //終了時刻（MMDD）
    private String end = null;
    //現在日時
    private String now = null;
    //開始時刻（MM）
    private int startH = 0;
    //開始時刻（DD）
    private int startM = 0;
    //終了時刻（MM）
    private int endH = 0;
    //終了時刻（DD）
    private int endM = 0;
    //労働時間（DD）
    private int workMinutes = 0;
    //残業時間（DD）
    private int overWorkMinutes = 0;
    //労働時間合計（M）
    private int totalWorkMinutes = 0;
    //残業時間合計（M）
    private int totalOverWorkMinutes = 0;
    //労働時間合計（M)と残業時間合計（M）を持つ配列
    private int[] totalNums = null;

    public void checkArgsLength(int length) {
        //引数が0だったらエラー出力
        if (length < 1) {
            throw new RuntimeException("引数が足りません");
        }
    }

    public void registryData(int argsNum, String date1, String date2, String date3) {
        if (argsNum < 4) {
            throw new RuntimeException("引数が足りません");
        }

        //日付（YYYYMMDD）
        date = date1;
        //開始時刻（MMDD）
        start = date2;
        //終了時刻（MMDD）
        end = date3;
        //現在日時
        now = LocalDateTime.now().toString();

        //開始時刻（MM）
        startH = Integer.valueOf(start.substring(0, 2));
        //開始時刻（DD）
        startM = Integer.valueOf(start.substring(2, 4));

        //終了時刻（MM）
        endH = Integer.valueOf(end.substring(0, 2));
        //終了時刻（DD）
        endM = Integer.valueOf(end.substring(2, 4));

        //労働時間（DD）
        workMinutes = endH * 60 + endM - (startH * 60 + startM);

        //12時台に退勤したら、分数は労働時間からカットする。
        //13時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        //18時台に退勤したら、分数は労働時間からカットする。
        //19時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        //21時台に退勤したら、分数は労働時間からカットする。
        //22時以降まで働いたら、休憩時間の60分間は労働時間からカットする。
        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }

        //残業時間（DD）
        overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);

        Datasource ds = new Datasource();
        ds.writeData(date, start, end, workMinutes, overWorkMinutes, now);

    }

    public void displayData(int argsNum, String yearMonth){

        if(argsNum < 2) {
            throw new RuntimeException("引数が足りません");
        }

        Datasource ds = new Datasource();
        totalNums = ds.readData(yearMonth);

        //労働時間合計（M）を取得
        totalWorkMinutes = totalNums[0];
        //残業時間合計（M）を取得
        totalOverWorkMinutes = totalNums[1];

        System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");

    }

}