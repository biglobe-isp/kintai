package jp.co.biglobe.isp.kintai.service.workrecord_registration;

import jp.co.biglobe.isp.kintai.domain.work_record.WorkRecordFactory;
import jp.co.biglobe.isp.kintai.domain.input_worktime.InputWorkTime;
import jp.co.biglobe.isp.kintai.service.work_regulation.WorkRegulationRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkRecordRegistrationService {
    private final WorkRecordRepository workRecordRepository;
    private final WorkRegulationRepository workRegulationRepository;
    private final WorkRecordFactory workRecordFactory;

    public void register(InputWorkTime inputWorkTime) {
        var workRegulation = workRegulationRepository.refer();
        var workRecord = workRecordFactory.create(inputWorkTime, workRegulation);
        workRecordRepository.persist(workRecord);
    }
}
