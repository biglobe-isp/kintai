package com.naosim.dddwork.domain;

public interface WorkTimeTotalRepository {
    WorkTimeTotalCalculation doWorktimeTaskExecute(WorkTimeTotalParam workTimeTotalParam);
}
