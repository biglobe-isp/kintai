package jp.co.biglobe.lib.essential.rest_api.error;

import jp.co.biglobe.lib.plugin.rest_exceptionhandler.RestExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * エラーページ
 */

@SuppressWarnings("ALL")
@RestController
public class ErrorApi {

    @Autowired
    private RestExceptionHandler restExceptionHandler;

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(value = "/error", method = RequestMethod.POST)
    public ResponseEntity<Map> invoke(HttpServletRequest request) {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        return restExceptionHandler.handleAndResponse(throwable);

    }

}
