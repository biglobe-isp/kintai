package kintai.api.nyuryoku.v1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import kintai.api.nyuryoku.NyuryokuAPI;
import kintai.domain.kintai.Kinmubi;
import kintai.domain.kintai.ShukkinJikoku;
import kintai.domain.kintai.TaikinJikoku;
import kintai.service.NyuryokuService;

public class V1NyuryokuAPI implements NyuryokuAPI {
    private NyuryokuService nyuryokuService;

    public V1NyuryokuAPI(NyuryokuService nyuryokuService) {
        this.nyuryokuService = nyuryokuService;
    }

    public void handle(String[] args) {
        DateTimeFormatter kinmubiFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter shukkinJikokuFormatter = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter taikinJikokuFormatter = DateTimeFormatter.ofPattern("HHmm");
        Kinmubi kinmubi = new Kinmubi(LocalDate.parse(args[1], kinmubiFormat));
        ShukkinJikoku shukkinJikoku = new ShukkinJikoku(LocalTime.parse(args[2], shukkinJikokuFormatter));
        TaikinJikoku taikinJikoku = new TaikinJikoku(LocalTime.parse(args[3], taikinJikokuFormatter));
        this.nyuryokuService.handle(kinmubi, shukkinJikoku, taikinJikoku);
    }
}