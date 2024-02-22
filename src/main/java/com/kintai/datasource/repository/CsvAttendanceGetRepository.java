package com.kintai.datasource.repository;

import com.kintai.datasource.entity.Attendance;
import com.kintai.domain.dto.AttendanceFactoryDto;
import com.kintai.domain.factory.IAttendanceFactory;
import com.kintai.domain.repository.IAttendanceGetRepository;
import com.kintai.exception.ValidatorException;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 勤怠データを取得するインターフェース{@link IAttendanceGetRepository}を実装したクラス
 * CSVファイルに登録されている勤怠データを取得します。
 */
public class CsvAttendanceGetRepository implements IAttendanceGetRepository {
    // 勤怠エンティティを生成するファクトリー
    private final IAttendanceFactory iAttendanceFactory;

    /**
     * コンストラクタ
     * 取得した勤怠データを勤怠エンティティに格納するための、ファクトリーインターフェース{@link IAttendanceFactory}をDIします。
     * @param iAttendanceFactory 勤怠エンティティ生成ファクトリーインターフェース
     */
    public CsvAttendanceGetRepository(IAttendanceFactory iAttendanceFactory) {
        this.iAttendanceFactory = iAttendanceFactory;
    }

    /**
     * 勤怠データをCSVファイルから取得するメソッド
     * 勤怠データが登録されているCSVファイルを読み込み{@link CsvAttendanceGetRepository#readToFile()}ます。
     * CSVファイルから勤怠データを取得{@link CsvAttendanceGetRepository#readToFile()}します。
     * 取得した勤怠データのうち、勤務日が重複するデータは除去{@link CsvAttendanceGetRepository#excludeDuplicationAttendance(List)}します。
     * 除去する対象勤怠データは登録日時が古いものとするため、取得した勤怠データを登録日時の降順でソート{@link CsvAttendanceGetRepository#sortAttendanceList(List)}します。
     *
     * @return 重複した勤怠日が除去された状態の勤怠データ(かつ登録日時が降順)リスト
     * @throws IOException 詳細は各メソッド参照
     * @throws ValidatorException 詳細は各メソッド参照
     * @throws ParseException 詳細は各メソッド参照
     */
    @Override
    public List<Attendance> get() throws IOException, ValidatorException, ParseException {
        BufferedReader reader = readToFile();
        List<Attendance> attendanceList = setAttendanceList(reader);
        List<Attendance> sortedAttendanceList = sortAttendanceList(attendanceList);
        return excludeDuplicationAttendance(sortedAttendanceList);
    }

    /**
     * 勤怠データを取得するためにCSVファイルをプログラム上に読み込みます。
     * @return プログラム上に読み込んだCSVファイルデータ
     * @throws IOException 読み込むファイルが存在しない場合、{@link IOException}をスローします。
     */
    protected BufferedReader readToFile() throws IOException {
        // ファイルを読み込むインスタンスを生成。対象は固定でdata.csvとする。
        File file = new File("data.csv");

        if(!file.exists()) {
            throw new IOException("data.csvファイルが存在しません。");
        }

        // ファイルをプログラム上から読み込めるようにする。
        FileReader fr = new FileReader(file);
        return new BufferedReader(fr);
    }

    /**
     * CSVファイルからプログラム上に読み込んだ勤怠データを勤怠エンティティに格納します。
     * 格納対象はCSVファイルに登録されている全勤怠データです。
     * @param br CSVファイルから読み込んだデータが格納されたインスタンス変数
     * @return 勤怠データリスト(重複データ除去前)
     * @throws IOException 読み込んだファイル内に想定外のデータがある場合は{@link IOException}をスローします。
     * @throws ValidatorException 読み込んだ勤怠データの各値に異常を検知した場合は{@link ValidatorException}をスローします。
     * @throws ParseException 読み込んだ勤怠データの勤怠日が日付解析できない場合は{@link ParseException}をスローします。
     */
    protected List<Attendance> setAttendanceList(BufferedReader br) throws IOException, ValidatorException, ParseException {
        List<Attendance> attendanceList = new ArrayList<>();
        // CSVファイルの１行目を取得
        String line = br.readLine();

        /*
        * CSVファイルから読み込んだデータを１行ずつ取り出し、勤怠エンティティに格納します。
        * 取得できる行がある限り、繰り返し処理。各行はカンマ区切り(,)で、以下の値が登録されています。
        * columns[0]：集計月
        * columns[1]：勤怠日
        * columns[2]：始業時刻
        * columns[3]：終業時刻
        * columns[4]：労働時間
        * columns[5]：残業時間
        * columns[6]：登録日時
        * */
        while(StringUtils.hasText(line)) {
            String[] columns = line.split(",");
            AttendanceFactoryDto attendanceFactoryDto = new AttendanceFactoryDto(columns[1],columns[2], columns[3]);
            attendanceFactoryDto.setTotalMonth(columns[0]);
            attendanceFactoryDto.setWorkMinutes(columns[4]);
            attendanceFactoryDto.setOverWorkMinute(columns[5]);
            attendanceFactoryDto.setLocalDateTime(columns[6]);
            attendanceList.add(iAttendanceFactory.makeAttendance(attendanceFactoryDto));

            // 次の行を取得
            line = br.readLine();
        }
        return attendanceList;
    }

    /**
     * 勤怠データを登録日時の降順でソートします。
     * @param attendanceList ソート前の勤怠データリスト
     * @return 登録日時の降順でソートされた勤怠データリスト
     */
    protected List<Attendance> sortAttendanceList(List<Attendance> attendanceList) {
        return attendanceList.stream().sorted((Comparator.comparing(Attendance::getCreateLocalDate)).reversed()).collect(Collectors.toList());
    }

    /**
     * 勤務日が重複した勤怠データを勤怠データリストから除去します。
     * @param attendanceList 重複除去前の勤怠データリスト
     * @return 重複除去後の勤怠データリスト
     */
    protected List<Attendance> excludeDuplicationAttendance(List<Attendance> attendanceList) {
        Set<String> unique = new HashSet<>();
        // 既にハッシュセットに登録されている勤怠日を持つ勤怠データは、リストから除去する。
        attendanceList.removeIf(attendance -> !unique.add(attendance.getWorkDate().getWorkDate()));
        return attendanceList;
    }
}
