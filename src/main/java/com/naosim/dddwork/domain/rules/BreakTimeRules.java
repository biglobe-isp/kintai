package com.naosim.dddwork.domain.rules;


import java.util.Vector;

public class BreakTimeRules {

    Vector<BreakTimeRule> breakTimeRules;

    public void addBreakTime(BreakTimeRule rule)
    {
       breakTimeRules.add(rule);
    }

    public Vector<BreakTimeRule> getBreakTimeRules()
    {
        return breakTimeRules;
    }

}
