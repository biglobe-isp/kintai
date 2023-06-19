package jp.co.biglobe.isp.kintai.datasource;

import jp.co.biglobe.isp.kintai.domain.work_regulation.BreakTime;
import jp.co.biglobe.isp.kintai.domain.work_regulation.RegulatedWorkTime;
import jp.co.biglobe.isp.kintai.domain.work_regulation.WorkRegulation;
import jp.co.biglobe.isp.kintai.service.work_regulation.WorkRegulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkRegulationSetting implements WorkRegulationRepository {
    @Override
    public WorkRegulation refer(){
        RegulatedWorkTime regulatedWorkTime = new RegulatedWorkTime(LocalTime.of(9,0), LocalTime.of(18, 0));
        List<BreakTime> breakTimeList = new ArrayList<>() {
            {
                add(new BreakTime(LocalTime.of(12, 0), LocalTime.of(13, 0)));
                add(new BreakTime(LocalTime.of(18, 0), LocalTime.of(19, 0)));
                add(new BreakTime(LocalTime.of(21, 0), LocalTime.of(22, 0)));
            }
        };
        return new WorkRegulation(regulatedWorkTime, breakTimeList);
    }
}
