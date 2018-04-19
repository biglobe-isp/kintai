package jp.co.biglobe.lib.publication.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@SuppressWarnings("ALL")
public interface HttpResponse {

    ResponseEntity<Map> build(HttpStatus status);

    ResponseEntity<Map> build(HttpStatus status, Map body);

    ResponseEntity<Map> buildResource(String uri, ResponsePostContainer response, Map body);

}
