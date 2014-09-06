package jp.co.biglobe.test.util.response;


import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CheckApiResponseAssert {

    public static void assertOkJsonPath(ResultActions resultActions, String detailType) throws Exception{
        assertJsonPath(resultActions, "ok", detailType);
    }

    public static void assertNgJsonPath(ResultActions resultActions, String detailType) throws Exception{
        assertJsonPath(resultActions, "ng", detailType);
    }

    private static void assertJsonPath(ResultActions resultActions, String result, String detailType) throws Exception{

        //resultActions.andExpect(content().string(""));
        resultActions.andExpect(jsonPath("$.header.statusCode").value("ok"));
        resultActions.andExpect(jsonPath("$.result").value(result));
        resultActions.andExpect(jsonPath("$.detail.type").value(detailType));
        resultActions.andExpect(jsonPath("$.detail.message").exists());
        resultActions.andExpect(jsonPath("$.error").doesNotExist());
    }
}
