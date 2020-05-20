package kintai.domain.kintai;

import java.time.LocalDateTime;

import kintai.domain.util.Clock;

public class NyuryokuJikoku {
    private LocalDateTime nyuryokuJikoku;

    public NyuryokuJikoku(LocalDateTime nyuryokuJikoku) {
        this.setNyuryokuJikoku(nyuryokuJikoku);
    }

    public static NyuryokuJikoku now(Clock clock) {
        return new NyuryokuJikoku(clock.now());
    }

    public LocalDateTime getNyuryokuJikoku() {
        return this.nyuryokuJikoku;
    }

    private void setNyuryokuJikoku(LocalDateTime nyuryokuJikoku) {
        this.nyuryokuJikoku = nyuryokuJikoku;
    }
}