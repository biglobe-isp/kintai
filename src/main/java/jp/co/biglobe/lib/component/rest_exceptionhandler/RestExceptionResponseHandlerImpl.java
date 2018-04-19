package jp.co.biglobe.lib.component.rest_exceptionhandler;

import jp.co.biglobe.lib.plugin.rest_exceptionhandler.RestExceptionResponseHandler;
import jp.co.biglobe.lib.plugin.rest_responce.error.HasErrorHttpStatusResponse;
import jp.co.biglobe.lib.plugin.rest_responce.error.RestErrorResponse;
import jp.co.biglobe.lib.plugin.rest_responce.error.RestHasErrorListResponse;
import jp.co.biglobe.lib.publication.exception.errorlist.HasRestErrorList;
import jp.co.biglobe.lib.publication.exception.errorstatus.HasHttpErrorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class RestExceptionResponseHandlerImpl implements RestExceptionResponseHandler {

    @Autowired
    private RestErrorResponse restErrorResponse;

    @Autowired
    private RestHasErrorListResponse hasErrorListResponse;

    @Autowired
    private HasErrorHttpStatusResponse hasErrorHttpStatusResponse;

    @Override
    public ResponseEntity<Map> handle(Throwable e) {

        // HasHttpErrorStatusを実装するException
        if (e instanceof HasHttpErrorStatus) {
            return hasErrorHttpStatusResponse.build((HasHttpErrorStatus) e);
        }

        if (e instanceof HasRestErrorList) {
            return hasErrorListResponse.build((HasRestErrorList) e);
        }

        return restErrorResponse.build(e);
    }
}
