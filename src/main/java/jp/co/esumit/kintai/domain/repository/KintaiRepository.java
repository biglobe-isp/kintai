package jp.co.esumit.kintai.domain.repository;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;

import java.util.List;

public interface KintaiRepository {
    void write(KintaiInfo kintaiInfo);
    List<KintaiInfo> read(TargetYearMonth targetYM);
}
