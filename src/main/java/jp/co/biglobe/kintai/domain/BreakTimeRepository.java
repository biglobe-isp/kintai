package jp.co.biglobe.kintai.domain;

import jp.co.biglobe.kintai.domain.breaktime.DailyBreakTimes;

public interface BreakTimeRepository {

    DailyBreakTimes findBreakTimes();
}
