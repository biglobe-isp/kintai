package jp.co.biglobe.lib.publication.exception.errorlist;

import org.springframework.http.HttpStatus;

import java.util.List;

@SuppressWarnings("ALL")
public interface HasRestErrorList {

    public HttpStatus getHttpStatus();

    public List<?> getErrorList();

}
