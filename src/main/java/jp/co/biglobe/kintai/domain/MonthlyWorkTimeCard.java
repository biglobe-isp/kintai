package jp.co.biglobe.kintai.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class MonthlyWorkTimeCard {

    private HashMap<String, WorkTime> timeCard;

    public MonthlyWorkTimeCard() {
        this.timeCard = new HashMap<>();
    }

    public WorkTime punch(String date, WorkTime workTime) {
        this.timeCard.put(date, workTime);
        return workTime;
    }

    public boolean isPunched() {
        return !this.timeCard.isEmpty();
    }

    public int getTotalMinutes() {
        ArrayList<WorkTime> timesList = new ArrayList(this.timeCard.entrySet());
        return timesList.stream().collect(Collectors.summingInt(workTime -> workTime.getMinutes()));
    }

    public int getTotalOverWorkMinutes() {
        ArrayList<WorkTime> timesList = new ArrayList(this.timeCard.entrySet());
        return timesList.stream().collect(Collectors.summingInt(workTime->workTime.getOverWorkMinutes()));
    }
}
