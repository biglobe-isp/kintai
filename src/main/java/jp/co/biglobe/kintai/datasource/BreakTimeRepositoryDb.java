package jp.co.biglobe.kintai.datasource;

import jp.co.biglobe.kintai.domain.BreakTimeRepository;
import jp.co.biglobe.kintai.domain.breaktime.BreakTime;
import jp.co.biglobe.kintai.domain.breaktime.DailyBreakTimes;
import jp.co.biglobe.kintai.util.Time;

public class BreakTimeRepositoryDb implements BreakTimeRepository {

    public DailyBreakTimes findBreakTimes(){
        DailyBreakTimes breakTimes = new DailyBreakTimes();
        breakTimes.add(new BreakTime(new Time(12,00),new Time(13,00)));
        breakTimes.add(new BreakTime(new Time(18,00),new Time(19,00)));
        breakTimes.add(new BreakTime(new Time(21,00),new Time(22,00)));
        return breakTimes;
    }
}
