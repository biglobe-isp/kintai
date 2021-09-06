package jp.co.esumit.kintai.domain.repository;

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.summary.MonthlySummary;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;

import java.util.List;

public interface KintaiRepository {
    void write(KintaiRecord kintaiRecord);
    List<KintaiRecord> read(TargetYearMonth targetYM);
}
