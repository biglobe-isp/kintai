package jp.co.biglobe.lib.publication.valueobject;

import jp.co.biglobe.lib.http.RestHttpStatus;
import jp.co.biglobe.lib.publication.exception.errorstatus.RestErrorStatus;

public interface ErrorStatusAndMessage extends RestErrorStatus, RestHttpStatus {

    default String getErrorMessage() {
        return getMessage();
    }

    default String getErrorMessage(ErrorKeyWordValue errorKeyWordValue) {
        return getMessage() + ":" + errorKeyWordValue.getErrorMessageValue();
    }

}
