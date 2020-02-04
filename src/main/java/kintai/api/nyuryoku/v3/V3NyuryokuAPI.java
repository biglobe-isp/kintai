package kintai.api.nyuryoku.v3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import kintai.api.nyuryoku.NyuryokuAPI;
import kintai.domain.kintai.Kinmubi;
import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.service.NyuryokuService;

public class V3NyuryokuAPI implements NyuryokuAPI {
    private NyuryokuService nyuryokuService;

    public V3NyuryokuAPI(NyuryokuService nyuryokuService) {
        this.nyuryokuService = nyuryokuService;
    }

    public void handle(String[] args) {
        DateTimeFormatter kinmubiFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter shukkinJikokuFormatter = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter taikinJikokuFormatter = DateTimeFormatter.ofPattern("HHmm");
        Kinmubi kinmubi = new Kinmubi(LocalDate.parse(args[1], kinmubiFormat));

        if (args[2].equals("v")) {
            // 休暇
            this.nyuryokuService.handle(kinmubi);
            return;
        }

        if (args[2].equals("am")) {
            // 午前休
            TaikinJikoku taikinJikoku = new TaikinJikoku(
                    LocalTime.parse(args[3].replace("-end:", ""), taikinJikokuFormatter));
            this.nyuryokuService.handle(kinmubi, taikinJikoku);
            return;
        }

        if (args[3].equals("pm")) {
            // 午後休
            ShukkinJikoku shukkinJikoku = new ShukkinJikoku(
                    LocalTime.parse(args[2].replace("-start:", ""), shukkinJikokuFormatter));
            this.nyuryokuService.handle(kinmubi, shukkinJikoku);
            return;
        }

        this.nyuryokuService.handle(kinmubi,
                new ShukkinJikoku(LocalTime.parse(args[2].replace("-start:", ""), shukkinJikokuFormatter)),
                new TaikinJikoku(LocalTime.parse(args[3].replace("-end:", ""), taikinJikokuFormatter)));

    }
}
