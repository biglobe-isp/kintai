package com.naosim.dddwork.domain.rules;


import com.naosim.dddwork.domain.time.Hour;
import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.RecordedTime;

import java.util.Vector;

public class BreakTimeRules {

    Vector<BreakTimeRule> breakTimeRulesVector;

    public BreakTimeRules()
    {
        // create break time rule
        RecordedTime breakTimeStart1 = new RecordedTime(new Hour(12), new Minute(0));
        RecordedTime breakTimeEnd1 = new RecordedTime(new Hour(13), new Minute(0));
        BreakTimeRule breakTimeRule1 = new BreakTimeRule(breakTimeStart1,breakTimeEnd1);

        RecordedTime breakTimeStart2 = new RecordedTime(new Hour(18), new Minute(0));
        RecordedTime breakTimeEnd2 = new RecordedTime(new Hour(19), new Minute(0));
        BreakTimeRule breakTimeRule2 = new BreakTimeRule(breakTimeStart2,breakTimeEnd2);

        RecordedTime breakTimeStart3 = new RecordedTime(new Hour(21), new Minute(0));
        RecordedTime breakTimeEnd3 = new RecordedTime(new Hour(22), new Minute(0));
        BreakTimeRule breakTimeRule3 = new BreakTimeRule(breakTimeStart3,breakTimeEnd3);

        breakTimeRulesVector = new Vector<BreakTimeRule>();

        breakTimeRulesVector.add(breakTimeRule1);
        breakTimeRulesVector.add(breakTimeRule2);
        breakTimeRulesVector.add(breakTimeRule3);
    }

    public Vector<BreakTimeRule> getBreakTimeRulesVector()
    {
        return breakTimeRulesVector;
    }

}
