package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.use_case.WorkTimeRegistrationApplication;

public interface LaborRegulationsRepository {
    LaborRegulations get(WorkTimeRegistrationApplication workTimeRegistrationApplication); //就業規則取得
}
