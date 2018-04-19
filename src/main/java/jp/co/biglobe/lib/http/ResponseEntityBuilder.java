package jp.co.biglobe.lib.http;

import jp.co.biglobe.lib.publication.http.ResponsePostContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ResponseEntityBuilder {

    ResponseEntity<Map> buildInternalServerError(Map body);

    @SuppressWarnings("unused")
    ResponseEntity<Map> buildOk(Map body);

    ResponseEntity<Map> buildByStatusAndBody(HttpStatus httpStatus, Map body);

    ResponseEntityBuilder buildResource(String uri, ResponsePostContainer response);

}
