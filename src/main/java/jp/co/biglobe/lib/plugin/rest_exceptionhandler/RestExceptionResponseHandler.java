package jp.co.biglobe.lib.plugin.rest_exceptionhandler;

import org.springframework.http.ResponseEntity;

import java.util.Map;

@SuppressWarnings("ALL")
public interface RestExceptionResponseHandler {
    public ResponseEntity<Map> handle(Throwable e);
}
