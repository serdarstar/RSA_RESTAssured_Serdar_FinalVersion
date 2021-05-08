package com.step_definitions;

import com.step_definitions.ApiStepDefs;
import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws FileNotFoundException {
        ApiStepDefs m=new ApiStepDefs();
        if(ApiStepDefs.place_id==null)
        {
        m.addPlacePayloadWith("Serdar","English","Glasgow");
        m.user_calls_with_post_http_request("addPlaceAPI","POST");
        m.verifyPlace_idCreatedMatchesWithUsingGetPlaceAPI("Serdar","getPlaceAPI");
        }
    }

}
