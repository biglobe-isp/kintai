package jp.co.biglobe.lib.http;

import jp.co.biglobe.lib.plugin.rest_view.RestJsonTemplate;
import jp.co.biglobe.lib.publication.http.HttpResponse;
import jp.co.biglobe.lib.publication.http.ResponsePostContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class HttpResponseImpl implements HttpResponse {

    @Autowired
    private RestJsonTemplate restJsonTemplate;

    @Autowired
    private ResponseEntityFactory responseEntityFactory;

    @Override
    public ResponseEntity<Map> build(HttpStatus status) {

        Map body = new HashMap();
        Map jsonResult = restJsonTemplate.build(body);

        return responseEntityFactory.createBuilder().buildByStatusAndBody(status, jsonResult);
    }

    @Override
    public ResponseEntity<Map> build(HttpStatus status, Map body) {

        Map jsonResult = restJsonTemplate.build(body);

        return responseEntityFactory.createBuilder().buildByStatusAndBody(status, jsonResult);
    }

    @Override
    public ResponseEntity<Map> buildResource(String uri, ResponsePostContainer response, Map body) {

        Map jsonResult = restJsonTemplate.build(body);

        return responseEntityFactory.createBuilder()
                .buildResource(uri, response)
                .buildByStatusAndBody(response.getHttpStatus(), jsonResult);
    }

}
