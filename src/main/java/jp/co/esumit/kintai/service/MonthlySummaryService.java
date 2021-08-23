package jp.co.esumit.kintai.service;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.MonthlySummary;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlySummaryService {
    public MonthlySummary getMonthlySummary(TargetYearMonth targetYM, KintaiRepository repository) {

        List<KintaiInfo> targetList = repository.read(targetYM);

        return MonthlySummary.create(targetList);
    }
}
