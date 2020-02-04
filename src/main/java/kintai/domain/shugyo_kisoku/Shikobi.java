package kintai.domain.shugyo_kisoku;

import java.time.LocalDate;

public class Shikobi {
    private LocalDate shikobi;

    public Shikobi(LocalDate shikobi) {
        this.setShikobi(shikobi);
    }

    public LocalDate getShikobi() {
        return this.shikobi;
    }

    private void setShikobi(LocalDate shikobi) {
        this.shikobi = shikobi;
    }

    public boolean isEqualsOrBefore(Shikobi other) {
        return this.shikobi.isEqual(other.shikobi) || this.shikobi.isBefore(other.shikobi);
    }
}