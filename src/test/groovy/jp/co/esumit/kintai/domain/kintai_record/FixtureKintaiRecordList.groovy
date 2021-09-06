package jp.co.esumit.kintai.domain.kintai_record

class FixtureKintaiRecordList {
    static List<KintaiRecord> get(String targetYm) {
        List<KintaiRecord> kintaiRecordList

        //test_data.csvの内容と一致した値を格納
        if (targetYm == "202101") {
            kintaiRecordList = new ArrayList<>();
            kintaiRecordList.add(FixtureKintaiRecord.get("20210101", "0900", "1800"))
            kintaiRecordList.add(FixtureKintaiRecord.get("20210102", "0900", "1800"))
            kintaiRecordList.add(FixtureKintaiRecord.get("20210103", "0900", "1800"))
            return kintaiRecordList;
        }

        //test_data.csvの内容と一致した値を格納
        if (targetYm == "202201") {
            kintaiRecordList = new ArrayList<>();
            kintaiRecordList.add(FixtureKintaiRecord.get("20220101", "0900", "1800"))
            kintaiRecordList.add(FixtureKintaiRecord.get("20210102", "0900", "1800"))
            return kintaiRecordList;
        }
    }
}
