package jp.co.biglobe.test.util.response;


import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UpdateApiResponseAssert {

    public static void assertJsonPath(ResultActions resultActions) throws Exception{
        //resultActions.andExpect(content().string(""));
        resultActions.andExpect(jsonPath("$.result").value("ok"));
        resultActions.andExpect(jsonPath("$.requestDate").exists());
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }
}
