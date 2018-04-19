package jp.co.biglobe.lib.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.biglobe.lib.danger.log.LoggerMessageFactory;
import jp.co.biglobe.lib.publication.http.ResponsePostContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResponseEntityFactory {

    private static final String HTTP_CONTENT_TYPE = "application/json;charset=UTF-8";

    @Autowired
    private LoggerMessageFactory loggerMessageFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntityBuilder createBuilder() {
        return new ResponseEntityBuilderImpl();
    }

    private void log(HttpStatus httpStatus, Map body) {
        try {
            logger.info(
                    loggerMessageFactory.createLoggerMessage(
                            "status=" + httpStatus.value() + " " +
                                    "body=" + new ObjectMapper().writeValueAsString(body)
                    ).createMessage()
            );
        } catch (JsonProcessingException e) {
            logger.error(
                    loggerMessageFactory.createLoggerMessage(
                            "レスポンスログの出力に失敗しました"
                    ).createMessage()
            );
        }

    }

    private class ResponseEntityBuilderImpl implements ResponseEntityBuilder {

        private final HttpHeaders headers = new HttpHeaders();

        private ResponseEntityBuilderImpl() {
            this.headers.add(HttpHeaders.CONTENT_TYPE, HTTP_CONTENT_TYPE);
        }

        public ResponseEntity<Map> buildInternalServerError(Map body) {
            log(HttpStatus.INTERNAL_SERVER_ERROR, body);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(headers)
                    .body(body);
        }

        public ResponseEntity<Map> buildOk(Map body) {
            log(HttpStatus.OK, body);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(body);
        }

        public ResponseEntity<Map> buildByStatusAndBody(HttpStatus httpStatus, Map body) {
            log(httpStatus, body);
            return ResponseEntity
                    .status(httpStatus)
                    .headers(headers)
                    .body(body);
        }

        public ResponseEntityBuilder buildResource(String uri, ResponsePostContainer response) {
            this.headers.add(HttpHeaders.LOCATION, response.getResource(uri).getValue());
            return this;
        }
    }

}
