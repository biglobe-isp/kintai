package jp.co.biglobe.kintai.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.ToIntFunction;

public class WorkTimeCardBuilder {

    private HashMap<String, DailyReport> timeCard;


    public WorkTimeCardBuilder() {
        this.timeCard = new HashMap<>();
    }

    public DailyReport punch(String date, DailyReport dailyReport) {
        this.timeCard.put(date, dailyReport);
        return dailyReport;
    }

    public Optional<MonthlyWorkTimeCard> build(){
        return isPunched() ? Optional.of(
                new MonthlyWorkTimeCard(
                        getSummary(DailyReport::getMinutes),
                        getSummary(DailyReport::getOverWorkMinutes)
                )) : Optional.empty();
    }

    boolean isPunched() {
        return !this.timeCard.isEmpty();
    }

    int getSummary(ToIntFunction<? super DailyReport> mapper){
        ArrayList<DailyReport> timesList = new ArrayList(this.timeCard.values());
        return timesList.stream().mapToInt(mapper).sum();
    }
}

