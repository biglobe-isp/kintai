package com.naosim.dddwork.datasource;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.File;

@EqualsAndHashCode
@ToString
public abstract class KintaiFileRepository {
    protected final File file = new File("data.csv");
}
