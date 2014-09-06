package jp.co.biglobe.test.util.response;


import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AuthenticationErrorResponseAssert {

    public static void assertJsonPath(ResultActions resultActions, String detailType, String message) throws Exception{
        //resultActions.andExpect(content().string(""));
        resultActions.andExpect(jsonPath("$.error").exists());
        resultActions.andExpect(jsonPath("$.error.type").value("AuthenticationException"));
        resultActions.andExpect(jsonPath("$.error.message").value(message));
        resultActions.andExpect(jsonPath("$.detail").exists());
        resultActions.andExpect(jsonPath("$.detail.type").value(detailType));
        resultActions.andExpect(jsonPath("$.detail.message").value(message));
        resultActions.andExpect(jsonPath("$.header.statusCode").value("authentication_error"));
    }
}
