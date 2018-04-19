package jp.co.biglobe.lib.publication.exception.errorstatus;


import org.springframework.http.HttpStatus;

@SuppressWarnings("ALL")
public interface HasHttpErrorStatus {
    public HttpStatus getHttpStatus();

    public String getErrorMessage();

    public ErrorType getErrorType();
}
