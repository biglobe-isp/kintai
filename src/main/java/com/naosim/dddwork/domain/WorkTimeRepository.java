package com.naosim.dddwork.domain;

public interface WorkTimeRepository<T> {
    T doExecute(String[] args);
}
