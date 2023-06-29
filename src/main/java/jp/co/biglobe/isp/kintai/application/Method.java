package jp.co.biglobe.isp.kintai.application;

public enum Method {
    INPUT("input"),
    TOTAL("total");

    private String type;
    Method(String type) {
        this.type = type;
    }

    public static Method findByType(String type) {
        for(Method method: Method.values()) {
            if(method.type.equals(type)) {
                return method;
            }
        }
        throw new RuntimeException("存在しないMethodです");
    }
}
