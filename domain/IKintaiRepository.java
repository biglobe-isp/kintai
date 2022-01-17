package com.naosim.dddwork.domain;

public interface IKintaiRepository {
    void save(Kintai kintai);
    KintaiMapModel getTotalWorkTimeMapsOf(String yearMonth);
}