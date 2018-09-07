package kintai.domain.company;

public enum KintaiCheckStatus {
    OK,
    出勤日ありえない,
    開始時刻が始業時刻を超えてる,
    終了時刻が日を跨いでる,
    終了時刻より開始時刻の方が遅い
}
