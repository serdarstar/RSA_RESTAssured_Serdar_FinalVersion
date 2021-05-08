package com.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import resources.APIResoruces_Enum;
import resources.TestDataBuild;
import resources.Utils;


public class ApiStepDefs extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    AddPlace place;
    TestDataBuild data=new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws FileNotFoundException {

        res=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address)); // response 2 ye ayrilmis durumda. body den sonra when kismi asagida

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String method) {

        // APIResources_Enum constructor will be called with the value coming from the method parameter
        APIResoruces_Enum resourceApi=APIResoruces_Enum.valueOf(resource);
        System.out.println("resourceApi = " + resourceApi);

        resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST"))
            {
                response=res.when().post(resourceApi.getResource());
            }
        else if (method.equalsIgnoreCase("GET"))
            {
                response=res.when().get(resourceApi.getResource());
            }
        else if (method.equalsIgnoreCase("DELETE"))
        {
            response=res.when().get(resourceApi.getResource());
        }
        else if (method.equalsIgnoreCase("PUT"))
        {
            response=res.when().get(resourceApi.getResource());
        }

    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(getJsonPath(response,keyValue), expectedValue);

    }


    @And("verify place_id created matches with {string} using {string}")
    public void verifyPlace_idCreatedMatchesWithUsingGetPlaceAPI(String expectedName, String resource) throws FileNotFoundException {

       place_id=getJsonPath(response,"place_id");
       res=given().spec(requestSpecification()).queryParam("place_id",place_id); // Burada request params belirlendi
       user_calls_with_post_http_request(resource, "GET");  // Burada yukaridaki step definition methodunu direk olarak cagiriyoruz ve GET api ya gidiyoruz
        // GET call yapildiktan sonra artik response degisti. Gidip yukaridak else if ler ile belirldigimiz yerde responsu GET response ile degistirecek
        // Simdi yeni responsedan bilgi aliyoruz
        String actualName=getJsonPath(response,"name");

        assertEquals(actualName, expectedName);


    }

    @Given("DeletePlace payload is sent")
    public void deleteplacePayloadIsSent() throws FileNotFoundException {
        res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}
