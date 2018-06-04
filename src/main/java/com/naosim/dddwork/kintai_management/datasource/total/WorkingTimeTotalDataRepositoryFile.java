package com.naosim.dddwork.kintai_management.datasource.total;

import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataRepository;
import com.naosim.dddwork.kintai_management.domain.duty.total.WorkingTimeTotalDataResult;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 勤怠集計情報リポジトリ実装
 */
@Repository
public class WorkingTimeTotalDataRepositoryFile implements WorkingTimeTotalDataRepository {

    @Override
    public void registTotalWorkingTime(WorkingTimeTotalDataResult workingTimeTotalResult) {

        File file = new File("totalData.csv");
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format("%s,%s,%s\n",
                    "集計年月日:" + workingTimeTotalResult.getTotalYearMonth().getValue(),
                    "勤務時間:" + workingTimeTotalResult.getTotalWorkingTime().getValue() / 60 + "時間" + workingTimeTotalResult.getTotalWorkingTime().getValue() % 60 + "分",
                    "残業時間:" + workingTimeTotalResult.getTotalOverWorkingTime().getValue() / 60 + "時間" + workingTimeTotalResult.getTotalOverWorkingTime().getValue() % 60 + "分"
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
