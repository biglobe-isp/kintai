package jp.co.biglobe.lib.http;

import jp.co.biglobe.lib.publication.valueobject.ResourceObject;

/**
 * リソース(URI)
 */
public class Resource {

    private final String value;

    private Resource(String value) {
        this.value = value;
    }

    public static Resource create(String uri, ResourceObject resourceObject) {
        return new Resource(uri + '/' + resourceObject.getResource());
    }

    public String getValue() {
        return value;
    }
}
