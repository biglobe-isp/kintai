package com.kintai.datasource.enums;

/**
 * 曜日列挙クラス
 */
public enum DayOfWeekKintai {
    MONDAY(0, "Monday"),
    TUESDAY(1, "Tuesday"),
    WEDNESDAY(2, "Wednesday"),
    THURSDAY(3, "Thursday"),
    FRIDAY(4, "Friday"),
    SATURDAY(5, "Saturday"),
    SUNDAY(6, "Sunday");

    // 曜日を一意に表す曜日コード
    private final int dayValue;
    // 曜日名
    private final String dayOfName;

    /**
     * コンストラクタ
     * @param dayValue 曜日コード
     * @param dayOfName 曜日名
     */
    DayOfWeekKintai(int dayValue, String dayOfName) {
        this.dayValue = dayValue;
        this.dayOfName = dayOfName;
    }

    /**
     * 曜日コードを取得するメソッド
     * @return 曜日コード
     */
    public int getDayValue() {
        return this.dayValue;
    }

    /**
     * 曜日名を取得するメソッド
     * @return 曜日名
     */
    public String getDayOfName() {
        return this.dayOfName;
    }

}
