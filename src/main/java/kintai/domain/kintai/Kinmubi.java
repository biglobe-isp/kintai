package kintai.domain.kintai;

import java.time.LocalDate;

public class Kinmubi {
    private LocalDate kinmubi;

    public Kinmubi(LocalDate kinmubi) {
        this.setKinmubi(kinmubi);
    }

    public LocalDate getKinmubi() {
        return this.kinmubi;
    }

    private void setKinmubi(LocalDate kinmubi) {
        this.kinmubi = kinmubi;
    };

}