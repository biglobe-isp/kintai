package com.naosim.dddwork.kintai_management.domain.duty.input;

/**
 * 勤怠情報登録インタフェース
 */
public interface WorkingTimeRegistrationRepository {

        void registWorkingTime(WorkingTimeRegistrationInput workingTimeRegistrationInput);
}
