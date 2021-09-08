package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.IRuleRepository;
import com.naosim.dddwork.domain.Rule;
import com.naosim.dddwork.domain.TimeSpan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CsvRuleRepository implements IRuleRepository {
    private CsvDb db;

    public CsvRuleRepository() {
        this.db = new CsvDb("rule");
    }

    @Override
    public List<Rule> getAll() {

        List<List<String>> allDatas = db.read();
        List<Rule> result = new ArrayList<>();

        for (List<String> columns : allDatas) {
            List<TimeSpan> rests = new ArrayList<>();
            if (StringUtils.isEmpty(columns.get(5))) rests.add(new TimeSpan(columns.get(5), columns.get(6)));
            if (StringUtils.isEmpty(columns.get(7))) rests.add(new TimeSpan(columns.get(7), columns.get(8)));
            if (StringUtils.isEmpty(columns.get(9))) rests.add(new TimeSpan(columns.get(9), columns.get(10)));

            Rule rule = new Rule(
                    columns.get(0),
                    new TimeSpan(columns.get(3), columns.get(4)),
                    StringUtils.isEmpty(columns.get(1)) ? null : columns.get(1),
                    StringUtils.isEmpty(columns.get(2)) ? null : columns.get(2),
                    rests
            );
            result.add(rule);
        }

        return result;
    }
}
