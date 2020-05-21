package kintai;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import kintai.api.nyuryoku.NyuryokuAPI;
import kintai.api.nyuryoku.v3.V3NyuryokuAPI;
import kintai.api.shukei.ShukeiAPI;
import kintai.datasource.csv.OreoreKintaiRepository;
import kintai.datasource.inmemory.InMemoryShugyoKisokuRepository;
import kintai.domain.kintai.KintaiRepository;
import kintai.domain.shugyo_kisoku.Shikobi;
import kintai.domain.shugyo_kisoku.ShugyoKisoku;
import kintai.domain.shugyo_kisoku.ShugyoKisokuRepository;
import kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku.KinmuJikanKisoku;
import kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku.KiteiKinmuJikan;
import kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku.ShigyoJikoku;
import kintai.domain.shugyo_kisoku.kinmu_jikan_kisoku.ShugyoJikoku;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiJikan;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KyukeiJikanKisoku;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiKaishiJikoku;
import kintai.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiShuryoJikoku;
import kintai.domain.util.Clock;
import kintai.service.NyuryokuService;
import kintai.service.ShukeiService;
import kintai.api.shukei.v1.V1ShukeiAPI;


class KadaiContainerInitializer implements ContainerInitializer {
    private KintaiRepository kintaiRepository = new OreoreKintaiRepository();
    private ShugyoKisokuRepository shugyoKisokuRepository = new InMemoryShugyoKisokuRepository();
    private Clock clock = new Clock(){
        public LocalDateTime now() {
            return LocalDateTime.now();
        }
    };
    private NyuryokuService inputService = new NyuryokuService(this.kintaiRepository, this.shugyoKisokuRepository,this.clock);
    private NyuryokuAPI inputAPI = new V3NyuryokuAPI(this.inputService);
    private ShukeiService shukeiService = new ShukeiService(this.kintaiRepository, this.clock);
    private ShukeiAPI shukeiAPI = new V1ShukeiAPI(this.shukeiService);
    
    public void init() {
        this.storeDefaultShugyoKisokus();
        Container.registerNyuryokuAPI(this.inputAPI);
        Container.registerShukeiAPI(this.shukeiAPI);
    }

    private void storeDefaultShugyoKisokus() {
        this.storeInitialShugyoKisoku();
        this.storeShugyoKisokuFrom20200315();
    }

    private void storeInitialShugyoKisoku() {
        ArrayList<KiteiKyukeiJikan> kiteiKyukeiJikanList = new ArrayList<KiteiKyukeiJikan>();
        kiteiKyukeiJikanList.add(
            new KiteiKyukeiJikan(
                new KiteiKyukeiKaishiJikoku(LocalTime.of(12, 00)),
                new KiteiKyukeiShuryoJikoku(LocalTime.of(13, 00))
            )
        );
        kiteiKyukeiJikanList.add(
            new KiteiKyukeiJikan(
                new KiteiKyukeiKaishiJikoku(LocalTime.of(18, 00)),
                new KiteiKyukeiShuryoJikoku(LocalTime.of(19, 00))
            )
        );
        kiteiKyukeiJikanList.add(
            new KiteiKyukeiJikan(
                new KiteiKyukeiKaishiJikoku(LocalTime.of(21, 00)),
                new KiteiKyukeiShuryoJikoku(LocalTime.of(22, 00))
            )
        );
        KyukeiJikanKisoku kyukeiJikanKisoku = new KyukeiJikanKisoku(kiteiKyukeiJikanList);
        KinmuJikanKisoku kinmuJikanKisoku = new KinmuJikanKisoku(new KiteiKinmuJikan(new ShigyoJikoku(LocalTime.of(9, 00)), new ShugyoJikoku(LocalTime.of(18, 00))));
        Shikobi lShikobi = new Shikobi(LocalDate.of(2000, 1, 1));
        ShugyoKisoku lShugyoKisoku = new ShugyoKisoku(kyukeiJikanKisoku, kinmuJikanKisoku, lShikobi);
        this.shugyoKisokuRepository.store(lShugyoKisoku);
    }

    private void storeShugyoKisokuFrom20200315() {
        ArrayList<KiteiKyukeiJikan> kiteiKyukeiJikanList = new ArrayList<KiteiKyukeiJikan>();
        kiteiKyukeiJikanList.add(
            new KiteiKyukeiJikan(
                new KiteiKyukeiKaishiJikoku(LocalTime.of(12, 00)),
                new KiteiKyukeiShuryoJikoku(LocalTime.of(13, 00))
            )
        );
        kiteiKyukeiJikanList.add(
            new KiteiKyukeiJikan(
                new KiteiKyukeiKaishiJikoku(LocalTime.of(15, 00)),
                new KiteiKyukeiShuryoJikoku(LocalTime.of(16, 00))
            )
        );
        kiteiKyukeiJikanList.add(
            new KiteiKyukeiJikan(
                new KiteiKyukeiKaishiJikoku(LocalTime.of(18, 00)),
                new KiteiKyukeiShuryoJikoku(LocalTime.of(19, 00))
            )
        );
        kiteiKyukeiJikanList.add(
            new KiteiKyukeiJikan(
                new KiteiKyukeiKaishiJikoku(LocalTime.of(21, 00)),
                new KiteiKyukeiShuryoJikoku(LocalTime.of(22, 00))
            )
        );
        KyukeiJikanKisoku kyukeiJikanKisoku = new KyukeiJikanKisoku(kiteiKyukeiJikanList);
        KinmuJikanKisoku kinmuJikanKisoku = new KinmuJikanKisoku(new KiteiKinmuJikan(new ShigyoJikoku(LocalTime.of(9, 00)), new ShugyoJikoku(LocalTime.of(18, 00))));
        // 新規則は施行日2030-03-15とする。
        Shikobi shikobi = new Shikobi(LocalDate.of(2030, 3, 15));
        ShugyoKisoku lShugyoKisoku = new ShugyoKisoku(kyukeiJikanKisoku, kinmuJikanKisoku, shikobi);
        this.shugyoKisokuRepository.store(lShugyoKisoku);
    }
}