package jp.co.biglobe.kintai.domain;

import java.util.Optional;

public interface WorkTimeRepository {
    void input(final WorkTime workTime);

    Optional<MonthlyWorkTimeCard> findWorkTimeCard(final String yearMonth);
}
