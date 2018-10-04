package jp.co.biglobe.kintai.domain.breaktime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DailyBreakTimes {

    private List<BreakTime> breakTimes;

    public DailyBreakTimes(){
        this.breakTimes = new ArrayList<>();
    }

    public boolean isEmpty(){
        return this.breakTimes.isEmpty();
    }

    public void add(BreakTime breakTime){
        this.breakTimes.add(breakTime);
    }

    public Stream stream(){
        return  this.breakTimes.stream();
    }


}
