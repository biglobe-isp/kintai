package kintai.api.nyuryoku.v2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import kintai.api.nyuryoku.NyuryokuAPI;
import kintai.domain.kintai.Kinmubi;
import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.service.NyuryokuService;

public class V2NyuryokuAPI implements NyuryokuAPI {
    private NyuryokuService nyuryokuService;

    public V2NyuryokuAPI(NyuryokuService nyuryokuService) {
        this.nyuryokuService = nyuryokuService;
    }

    public void handle(String[] args) {
        DateTimeFormatter kinmubiFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter shukkinJikokuFormatter = DateTimeFormatter.ofPattern("HH_mm");
        DateTimeFormatter taikinJikokuFormatter = DateTimeFormatter.ofPattern("HH_mm");
        Kinmubi kinmubi = new Kinmubi(LocalDate.parse(args[1].replace("-date:", ""), kinmubiFormat));
        ShukkinJikoku shukkinJikoku = new ShukkinJikoku(
                LocalTime.parse(args[2].replace("-start:", ""), shukkinJikokuFormatter));
        TaikinJikoku taikinJikoku = new TaikinJikoku(
                LocalTime.parse(args[3].replace("-end:", ""), taikinJikokuFormatter));
        this.nyuryokuService.handle(kinmubi, shukkinJikoku, taikinJikoku);
    }
}
