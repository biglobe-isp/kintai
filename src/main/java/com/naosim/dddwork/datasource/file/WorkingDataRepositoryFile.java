package com.naosim.dddwork.datasource.file;

import com.naosim.dddwork.domain.duty.WorkingData;
import com.naosim.dddwork.domain.duty.WorkingDataRepository;
import com.naosim.dddwork.domain.duty.WorkingSummaryData;
import com.naosim.dddwork.domain.word.WorkDate;
import com.naosim.dddwork.domain.word.WorkingMinute;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 勤怠管理API　勤務データ読み書きRepository実装
 */
@Repository
public class WorkingDataRepositoryFile implements WorkingDataRepository {

    public void write(WorkingData workingData) throws Exception {

        ArrayList<WorkingData> workingDataList = read();
        ArrayList<WorkingData> workingDataListNew = new ArrayList<>();

        if (!workingDataList.isEmpty()) {

            boolean isInputDataSetted = false;

            for (int n = 0; n < workingDataList.size(); n++) {

                WorkingData workingDataOriginal = workingDataList.get(n);

                // [2] 入力された出勤日が保持した内容に存在する場合、行を差し替え
                if (workingData.getWorkDate().convertLocalDate()
                        .compareTo(workingDataOriginal.getWorkDate().convertLocalDate()) == 0) {
                    if (!isInputDataSetted) {
                        workingDataListNew.add(workingData);
                        isInputDataSetted = true;
                    }
                }
                // [3] 入力された出勤日が保持した内容に存在しない場合
                else {

                    // [3-1] 入力された出勤日が現在行の出勤日より前の場合、前に追加
                    if (workingData.getWorkDate().convertLocalDate()
                            .compareTo(workingDataOriginal.getWorkDate().convertLocalDate()) < 0) {

                        if (!isInputDataSetted) {
                            workingDataListNew.add(workingData);
                            isInputDataSetted = true;
                        }
                        workingDataListNew.add(
                                workingDataOriginal
                        );
                    }
                    // [3-2] 入力された出勤日が現在行の出勤日より後の場合、次の行へ（最終行までループ）
                    else if (workingData.getWorkDate().convertLocalDate()
                            .compareTo(workingDataOriginal.getWorkDate().convertLocalDate()) > 0) {
                        workingDataListNew.add(workingDataOriginal);
                    }
                }
            }

            if (!isInputDataSetted) {
                workingDataListNew.add(workingData);
            }
        } else {
            workingDataListNew.add(workingData);
        }

        // [5] 詰め替えたリストをファイルに出力
        // TODO 環境ごとにパスを設定する
        File file = new File("src/test/resources/csv/data.csv");

        try (FileWriter fileWriter = new FileWriter(file, false)) {

//            // １行目に項目名を追加
//            String title = "\"出勤日\",\"出勤時間\",\"退勤時間\",\"勤務時間\",\"残業時間\"\n";
//            fileWriter.write(title);

            if (!workingDataListNew.isEmpty()) {
                for (int n = 0; n < workingDataListNew.size(); n++) {
                    // 出勤日
                    fileWriter.write("\"" + workingDataListNew.get(n).getWorkDate().getValue().toString() + "\",");
                    // 出勤時間
                    fileWriter.write("\"" + workingDataListNew.get(n).getWorkTimeFrom().getValue().toString() + "\",");
                    // 退勤時間
                    fileWriter.write("\"" + workingDataListNew.get(n).getWorkTimeTo().getValue().toString() + "\",");
                    // 勤務時間
                    fileWriter.write("\"" + workingDataListNew.get(n).getWorkingMinute().getValue().toString() + "\",");
                    // 残業時間
                    fileWriter.write("\"" + workingDataListNew.get(n).getOverWorkingMinute().getValue().toString() + "\"\n");
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public ArrayList<WorkingData> read() throws Exception {

        // TODO 環境ごとにパスを設定する
        File file = new File("src/test/resources/csv/data.csv");

        // [1] ファイルが存在している場合、内容を保持
        ArrayList<WorkingData> workingDataList = new ArrayList<>();
        if (file.length() > 0) {
            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr)
            ) {
                String line;
                while ((line = br.readLine()) != null) {
                    workingDataList.add(new WorkingData(line));
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

        return workingDataList;
    }

    public WorkingSummaryData summary(ArrayList<WorkingData> workingDataList) {

        BigDecimal workMinuteSummary = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal overWorkMinuteSummary = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

        // workingData内の勤務時間と残業時間を集計する
        int startYear = 0;
        int startMonth = 0;
        int startDay = 0;
        int endYear = 0;
        int endMonth = 0;
        int endDay = 0;

        // 締日
        final int CLOSING_DAY = 15;

        if (!workingDataList.isEmpty()) {

            for (int n = 0; n < workingDataList.size(); n++) {

                // -----------------------------
                // 集計期間の算出
                // -----------------------------
                if (n == 0) {

                    startYear = workingDataList.get(n).getWorkDate().getValue().getYear();
                    startMonth = workingDataList.get(n).getWorkDate().getValue().getMonth().getValue();

                    // 集計開始月が12月の場合（xxx0年12月16日〜xxx1年1月15日）
                    if (startMonth == 12) {
                        endYear = startYear + 1;
                        endMonth = 1;
                    } else {
                        endYear = startYear;
                        endMonth = startMonth + 1;
                    }
                    startDay = CLOSING_DAY + 1;
                    endDay = CLOSING_DAY;
                }

                LocalDate start = LocalDate.of(startYear, startMonth, startDay);
                LocalDate end = LocalDate.of(endYear, endMonth, endDay);

                // 締日より前の分は読み飛ばす
                if (workingDataList.get(n).getWorkDate().getValue().compareTo(start) < 0) {
                    continue;
                }
                // 締日分まで加算して終了
                else if (workingDataList.get(n).getWorkDate().getValue().compareTo(end) == 0) {
                    workMinuteSummary = workMinuteSummary.add(workingDataList.get(n).getWorkingMinute().getValue());
                    overWorkMinuteSummary = overWorkMinuteSummary.add(workingDataList.get(n).getOverWorkingMinute().getValue());
                    break;
                }
                // 期間内なので加算
                else {
                    workMinuteSummary = workMinuteSummary.add(workingDataList.get(n).getWorkingMinute().getValue());
                    overWorkMinuteSummary = overWorkMinuteSummary.add(workingDataList.get(n).getOverWorkingMinute().getValue());
                }
            }
        }

        return new WorkingSummaryData(
                new WorkDate(LocalDate.of(startYear, startMonth, startDay).toString().replaceAll("-", "")),
                new WorkDate(LocalDate.of(endYear, endMonth, endDay).toString().replaceAll("-", "")),
                new WorkingMinute(workMinuteSummary),
                new WorkingMinute(overWorkMinuteSummary)
        );
    }
}
