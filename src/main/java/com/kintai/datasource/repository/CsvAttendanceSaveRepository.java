package com.kintai.datasource.repository;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.repository.IAttendanceSaveRepository;

import java.io.File;
import java.io.FileWriter;

/**
 * 勤怠データを登録するインターフェース{@link IAttendanceSaveRepository}を実装したクラス
 * 勤怠データをCSVファイルに登録します。
 */
public class CsvAttendanceSaveRepository implements IAttendanceSaveRepository {
    /**
     * １件分の勤怠データ{@link Attendance}を登録します。
     * @param attendance 勤怠データを格納しているエンティティクラス。
     * @throws Exception 詳細は各メソッド参照
     */
    @Override
    public void save(Attendance attendance) throws Exception {
        saveToFile(attendance);
    }

    /**
     * CSVファイルに１件分の勤怠データを登録します。
     * @param attendance 登録対象勤怠データ
     * @throws Exception ファイルへの書き込みに失敗した場合、{@link Exception}をスローします。
     */
    protected void saveToFile(Attendance attendance) throws Exception {
        // ファイルを書き込むインスタンスを生成。対象は固定でdata.csvとする。
        File file = new File("data.csv");
        FileWriter filewriter = new FileWriter(file, true);

        /*
        * １件分の勤怠データをCSVファイル上に登録します。
        * 勤怠データをカンマ区切りで一つの文字列とし、１行分として書き込みます。
        * 末尾に改行文字(\n)を入れ、次登録される勤怠データは２行目以降に書き込まれます。
        * 書き込む値は以下の通り。また以下の順番でカンマ区切りで書き込みます。
        * ・集計月
        * ・勤怠日
        * ・始業時刻
        * ・終業時刻
        * ・労働時間
        * ・残業時間
        * ・登録日時
        * 既にCSVファイル上に書き込まれた勤怠日があっても、書き込むことを許可。
        * */
        filewriter.write(String.format(
                "%s,%s,%s,%s,%s,%s,%s\n",
                attendance.getTotalMonth().getTotalMonth(),
                attendance.getWorkDate().getWorkDate(),
                attendance.getWorkTime().getStartTime().getStartTime(),
                attendance.getWorkTime().getEndTime().getEndTime(),
                attendance.getWorkMinutes().getWorkMinutes(),
                attendance.getOverWorkMinutes().getOverWorkMinutes(),
                attendance.getCreateLocalDate()
        ));
        filewriter.close();
    }
}
