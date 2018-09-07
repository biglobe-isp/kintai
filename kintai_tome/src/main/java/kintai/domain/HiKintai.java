package kintai.domain;

public class HiKintai {

    private SyukkinDate syukkinDate;
    private SyukkinTime syukkinTime;
    private TaikinTime taikinTime;
    private WorkMinutes workMinutes;
    private AllBreakMinutes allBreakMinutes;

    public HiKintai(SyukkinDate syukkinDate, SyukkinTime syukkinTime, TaikinTime taikinTime, WorkMinutes workMinutes, AllBreakMinutes allBreakMinutes) {
        this.syukkinDate = syukkinDate;
        this.syukkinTime = syukkinTime;
        this.taikinTime = taikinTime;
        this.workMinutes = workMinutes;
        this.allBreakMinutes = allBreakMinutes;
    }

    public static HiKintai create(String date, String start, String end) {

        SyukkinTime syukkinTime = new SyukkinTime(start);
        TaikinTime taikinTime = new TaikinTime(end);
        AllBreakMinutes allBreakMinutes = AllBreakMinutes.create(taikinTime);

        return new HiKintai(
                new SyukkinDate(date),
                new SyukkinTime(start),
                new TaikinTime(end),
                WorkMinutes.create(syukkinTime, taikinTime, allBreakMinutes),
                allBreakMinutes
        );

    }

    public SyukkinDate getSyukkinDate() {
        return syukkinDate;
    }

    public SyukkinTime getSyukkinTime() {
        return syukkinTime;
    }

    public TaikinTime getTaikinTime() {
        return taikinTime;
    }

    public WorkMinutes getWorkMinutes() {
        return workMinutes;
    }

    public AllBreakMinutes getAllBreakMinutes() {
        return allBreakMinutes;
    }

    public FixedMinutes getFixedMinutes() {
        return workMinutes.getFixedMinutes();
    }

    public OverWorkMinutes getOverWorkMinutes() {
        return workMinutes.getOverWorkMinutes();
    }

}
