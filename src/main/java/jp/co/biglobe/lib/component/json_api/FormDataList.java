package jp.co.biglobe.lib.component.json_api;

import jp.co.biglobe.lib.publication.json_api.RequestParameters;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@SuppressWarnings("ALL")
@ToString(includeFieldNames = false)
public class FormDataList {

    @Getter
    private RequestParameters parameters = new RequestParameters();

    public FormDataList(final String domainId, final String hostname, final String url) {
        parameters
                .add("ispClientDomainIdentifier", domainId)
                .add("ispClientServerIdentifier", hostname)
                .add("ispClientPath", url);
    }

    public FormDataList addString(final String key, final String value) {
        parameters.add(key, value);
        return this;
    }

    public FormDataList addString(final String key, final Optional<String> optValue) {
        optValue.ifPresent(value -> addString(key, value));
        return this;
    }

    public FormDataList addObject(final String key, JsonApiClientParameter jsonApiClientParameter) {
        addString(key, jsonApiClientParameter.getJsonApiValue());

        return this;
    }

    public <T extends JsonApiClientParameter> FormDataList addObject(String key, Optional<T> optHttpClientParameter) {
        optHttpClientParameter.ifPresent(httpClientParameter -> addObject(key, httpClientParameter));

        return this;
    }
}
