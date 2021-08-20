package kintai.domain.kintai;

import java.time.LocalTime;

public class ShukkinJikoku {
    private LocalTime shukkinJikoku;

    public ShukkinJikoku(LocalTime shukkinJikoku) {
        this.setShukkinJikoku(shukkinJikoku);
    }

    public LocalTime getShukkinJikoku() {
        return this.shukkinJikoku;
    }

    private void setShukkinJikoku(LocalTime shukkinJikoku) {
        this.shukkinJikoku = shukkinJikoku;
    }
}