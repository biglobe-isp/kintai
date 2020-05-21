package kintai.domain.kintai;

import java.time.LocalTime;

public class TaikinJikoku {
    private LocalTime taikinJikoku;

    public TaikinJikoku(LocalTime taikinJikoku) {
        this.setTaikinJikoku(taikinJikoku);
    }

    public LocalTime getTaikinJikoku() {
        return this.taikinJikoku;
    }

    private void setTaikinJikoku(LocalTime taikinJikoku) {
        this.taikinJikoku = taikinJikoku;
    }
}