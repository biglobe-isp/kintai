package jp.co.biglobe.lib.publication.date;

@SuppressWarnings("ALL")
public enum DateFormatPattern {
    yyyyMMddHHmmss("uuuuMMddHHmmss"),
    yyyyMMddHHmm("uuuuMMddHHmm"),
    yyyyMMddHH("uuuuMMddHH"),
    yyyyMMdd("yyyyMMdd");

    private final String value;

    private DateFormatPattern(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
