package jp.co.biglobe.kintai.domain;

public final class WorkTimeBuilder {

    public WorkTimeBuilder() {
    }

    public WorkTime build(WorkTime workTime, int minutes, int overWorkMinutes) {
        return new WorkTime(
                workTime.getDate(),
                workTime.getStartTime(),
                workTime.getEndTime(),
                minutes,
                overWorkMinutes,
                workTime.getNow()
        );
    }
}
