package jp.co.esumit.kintai.service;

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlySummaryService {
    private final KintaiRepository repository;

    public List<KintaiRecord> getRecordList(TargetYearMonth targetYM) {

        return repository.read(targetYM);
    }
}
