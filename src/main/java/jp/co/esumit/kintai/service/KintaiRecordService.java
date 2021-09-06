package jp.co.esumit.kintai.service;

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.kintai_record.Recorder;
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime;
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KintaiRecordService {
    private final KintaiRepository repository;
    private final Recorder recorder;

    public void persist(TargetDay targetDay, StartTime startTime, EndTime endTime) {

        KintaiRecord info = recorder.record(targetDay, startTime, endTime);

        repository.write(info);
    }
}
