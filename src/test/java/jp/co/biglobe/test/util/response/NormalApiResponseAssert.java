package jp.co.biglobe.test.util.response;


import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class NormalApiResponseAssert {

    public static void assertJsonPath(ResultActions resultActions) throws Exception{

        //resultActions.andExpect(content().string(""));
        resultActions.andExpect(jsonPath("$.header.statusCode").value("ok"));
        resultActions.andExpect(jsonPath("$.header.requestId").exists());
        resultActions.andExpect(jsonPath("$.header.hostName").exists());
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }
}
