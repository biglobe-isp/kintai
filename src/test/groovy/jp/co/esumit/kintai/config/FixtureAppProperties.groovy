package jp.co.esumit.kintai.config

class FixtureAppProperties {
    static AppProperties get() {
        def appProperties = new AppProperties();
        AppProperties.metaClass.setAttribute(appProperties, "kintaiDataCsv", "/projects/kintai/src/test/resources/test_data1.csv")
        return appProperties
    }
}
