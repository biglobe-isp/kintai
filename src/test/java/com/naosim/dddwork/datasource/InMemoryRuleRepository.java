package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.IRuleRepository;
import com.naosim.dddwork.domain.RuleId;
import com.naosim.dddwork.domain.Rule;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRuleRepository implements IRuleRepository {
    private Map<RuleId, Rule> data = new HashMap<>();

    public void add(Rule rule) {
        data.put(rule.getRuleId(), rule);
    }

    @Override
    public List<Rule> getAll() {
        return new ArrayList<>(data.values());
    }

    public void clear() {
        data = new HashMap<>();
    }
}
