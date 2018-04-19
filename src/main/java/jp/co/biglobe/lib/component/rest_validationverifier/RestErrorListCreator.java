package jp.co.biglobe.lib.component.rest_validationverifier;

import jp.co.biglobe.lib.danger.validationverifier.RestValidationErrorDetail;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
final class RestErrorListCreator {

    List<RestValidationErrorDetail> create(List<FieldError> fieldErrors) {
        List<RestValidationErrorDetail> result = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            RestValidationErrorDetail validationErrorDetail = RestValidationErrorDetail.builder()
                    .setValidatedField(fieldError.getField())
                    .setRejectedReason(fieldError.getCode())
                    .setRejectedValue(fieldError.getRejectedValue())
                    .setMessage(fieldError.getDefaultMessage())
                    .build();
            result.add(validationErrorDetail);
        }
        return result;
    }

}
