package jp.co.biglobe.kintai.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.ToIntFunction;

public class WorkTimeCardBuilder {

    private HashMap<String, WorkTime> timeCard;


    public WorkTimeCardBuilder() {
        this.timeCard = new HashMap<>();
    }

    public WorkTime punch(String date, WorkTime workTime) {
        this.timeCard.put(date, workTime);
        return workTime;
    }

    public Optional<MonthlyWorkTimeCard> getMonthlyWorkTimCard(){
        return isPunched() ? Optional.of(
                new MonthlyWorkTimeCard(
                        getSummary(WorkTime::getMinutes),
                        getSummary(WorkTime::getOverWorkMinutes)
                )) : Optional.empty();
    }

    private boolean isPunched() {
        return !this.timeCard.isEmpty();
    }

    private int getSummary(ToIntFunction<? super WorkTime> mapper){
        ArrayList<WorkTime> timesList = new ArrayList(this.timeCard.values());
        return timesList.stream().mapToInt(mapper).sum();
    }
}

