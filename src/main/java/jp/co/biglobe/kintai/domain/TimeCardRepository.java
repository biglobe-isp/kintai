package jp.co.biglobe.kintai.domain;

import java.util.Optional;

public interface TimeCardRepository {

    Optional<MonthlyWorkTimeCard> findWorkTimeCard(final YearMonth yearMonth);
}
