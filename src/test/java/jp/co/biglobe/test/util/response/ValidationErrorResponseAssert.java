package jp.co.biglobe.test.util.response;


import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ValidationErrorResponseAssert {

    public static void assertJsonPath(ResultActions resultActions, String errorField) throws Exception{
//        resultActions.andExpect(content().string(""));
        resultActions.andExpect(jsonPath("$.error.type").value("AlarmValidationException"));
        resultActions.andExpect(jsonPath("$.error.message").value("バリデーションエラーが発生しました"));
        resultActions.andExpect(jsonPath("$.header.statusCode").value("validation_error"));
        resultActions.andExpect(jsonPath("$.detail[0].validatedField").value(errorField));
    }
}

