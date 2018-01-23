package com.naosim.dddwork.domain;

public interface WorkTimeRepository<T, S> {
    T doWorktimeTaskExecute(S objet);
}
