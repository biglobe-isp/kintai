package jp.co.biglobe.lib.publication.http;

import jp.co.biglobe.lib.http.Resource;
import jp.co.biglobe.lib.http.RestHttpStatus;
import jp.co.biglobe.lib.publication.valueobject.ResourceObject;
import org.springframework.http.HttpStatus;

/**
 * レスポンス情報用のinterface(POST用)
 */
public interface ResponsePostContainer {

    ResourceObject getResourceObject();

    RestHttpStatus getRestHttpStatus();

    default HttpStatus getHttpStatus() {
        return getRestHttpStatus().getHttpStatus();
    }

    default Resource getResource(String uri) {
        return Resource.create(uri, getResourceObject());
    }

}
