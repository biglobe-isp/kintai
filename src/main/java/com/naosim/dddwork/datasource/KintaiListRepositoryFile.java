package com.naosim.dddwork.datasource;

import com.naosim.dddwork.datasource.file.KintaiFile;
import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.time.work.WorkDate;
import com.naosim.dddwork.domain.kintai.totalprint.KintaiOfDays;
import com.naosim.dddwork.domain.kintai.totalprint.repository.KintaiListRepository;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class KintaiListRepositoryFile implements KintaiListRepository {

    @Override
    public KintaiOfDays get() {
        File kintaiCsvFile = KintaiFile.getTargetCsv();

        try (
                FileReader fr = new FileReader(kintaiCsvFile);
                BufferedReader br = new BufferedReader(fr)
        ) {
            List<KintaiOfOneDay> kintaiOfOneDayLineList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                kintaiOfOneDayLineList.add(new KintaiOfOneDay(line));
            }

            return new KintaiOfDays(this.getDuplicateRemoved(kintaiOfOneDayLineList));

        } catch (FileNotFoundException e) {
            throw new RuntimeException("対象ファイルが存在しません");
        } catch (IOException e) {
            throw new RuntimeException("ファイルの読み込みに失敗しました");
        }
    }

    private List<KintaiOfOneDay> getDuplicateRemoved(List<KintaiOfOneDay> duplicateExistList) {
        // 登録日時でソートするために使用
        Comparator<KintaiOfOneDay> comparator = Comparator.comparing(kintaiOfOneDay -> kintaiOfOneDay.getRegisterDateTime().getValue());

        // 重複チェックのために使用するSet
        Set<WorkDate> workDateSet = new HashSet<>();

        // 対象データを抽出して新たにリスト化
        return duplicateExistList.stream()
                .sorted(comparator.reversed())
                // 重複データ（同一勤務日）は後勝ちとし、最新データ以外は削除
                .filter(e -> workDateSet.add(e.getWorkDate()))
                .collect(Collectors.toList());
    }
}
