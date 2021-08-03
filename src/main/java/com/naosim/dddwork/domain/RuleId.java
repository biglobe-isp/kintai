package com.naosim.dddwork.domain;

import java.util.UUID;

public class RuleId {
    private final String id;

    public RuleId() {
        this.id = UUID.randomUUID().toString();
    }

    public RuleId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
