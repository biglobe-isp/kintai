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
 * 勤怠データを取得するリポジトリ
 * 本クラスでは勤怠データをCSVファイルから取得する
 */
public class CsvAttendanceGetRepository implements IAttendanceGetRepository {
    // 勤怠エンティティを生成するファクトリー
    private final IAttendanceFactory iAttendanceFactory;

    /**
     * コンストラクタ
     * @param iAttendanceFactory 勤怠エンティティ生成ファクトリー
     */
    public CsvAttendanceGetRepository(IAttendanceFactory iAttendanceFactory) {
        this.iAttendanceFactory = iAttendanceFactory;
    }

    /**
     * 勤怠データをCSVファイルから取得するメソッド
     * 本メソッドでは、取得した勤怠データのうち、勤務日が重複するデータは除去する。
     * 除去するのは常に登録日時が古い勤怠データとする。
     *
     * @return 勤怠データ
     * @throws IOException IOException
     * @throws ValidatorException 検証エラー
     * @throws ParseException 日付パースエラー
     */
    @Override
    public List<Attendance> get() throws IOException, ValidatorException, ParseException {
        // CSVファイル読み込み
        BufferedReader reader = readToFile();
        // 勤怠データ取得
        List<Attendance> attendanceList = getAttendanceList(reader);
        // 取得した勤怠データをソート。重複している勤怠データを素早く除去するために。
        List<Attendance> sortedAttendanceList = sortAttendanceList(attendanceList);
        // 勤務日が重複する勤怠データを除去
        List<Attendance> excludeDuplicationAttendanceList = excludeDuplicationAttendance(sortedAttendanceList);
        return excludeDuplicationAttendanceList;
    }

    /**
     * 勤怠データを取得するためにCSVファイルを読み込む
     * @return CSVファイル
     * @throws IOException 読み込むファイルが存在しない場合、IOエラーをスロー
     */
    protected BufferedReader readToFile() throws IOException {
        // ファイルを読み込むインスタンスを生成。対象は固定でdata.csvとする。
        File file = new File("data.csv");

        // ファイル存在確認
        if(!file.exists()) {
            throw new IOException("data.csvファイルが存在しません。");
        }

        // ファイルをプログラム上から読み込めるようにする。
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        return br;
    }

    /**
     * CSVファイルから勤怠データを読み込む。
     * @param br CSVファイルから読み込んだデータが格納されたインスタンス変数
     * @return 勤怠データリスト(重複データ除去前)
     * @throws IOException 読み込んだファイル内に想定外のデータがある場合はIOエラーをスローする
     * @throws ValidatorException
     * @throws ParseException
     */
    protected List<Attendance> getAttendanceList(BufferedReader br) throws IOException, ValidatorException, ParseException {
        List<Attendance> attendanceList = new ArrayList<>();
        String line = br.readLine();
        while(StringUtils.hasText(line)) {
            String[] columns = line.split(",");
            AttendanceFactoryDto attendanceFactoryDto = new AttendanceFactoryDto(columns[1],columns[2], columns[3]);
            attendanceFactoryDto.setTotalMonth(columns[0]);
            attendanceFactoryDto.setWorkMinutes(columns[4]);
            attendanceFactoryDto.setOverWorkMinute(columns[5]);
            attendanceFactoryDto.setLocalDateTime(columns[6]);
            attendanceList.add(iAttendanceFactory.makeAttendance(attendanceFactoryDto));
            line = br.readLine();
        }
        return attendanceList;
    }

    protected List<Attendance> sortAttendanceList(List<Attendance> attendanceList) {
        // 登録日時の降順でソート。同じ勤務日で再登録ができるため、集計する際は登録日が最新のデータで集計するため。
        return attendanceList.stream().sorted((Comparator.comparing(Attendance::getCreateLocalDate)).reversed()).collect(Collectors.toList());
    }

    protected List<Attendance> excludeDuplicationAttendance(List<Attendance> attendanceList) throws IOException, ValidatorException {
        // 勤務日が重複するデータは古いデータを除外
        Set<String> unique = new HashSet<>();
        attendanceList.removeIf(attendance -> !unique.add(attendance.getWorkDate().getWorkDate()));
        return attendanceList;
    }
}
