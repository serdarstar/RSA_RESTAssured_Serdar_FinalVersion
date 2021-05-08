package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import io.restassured.response.Response;

public class Utils {

    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws FileNotFoundException {

        if (req==null){
            PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
            req=new RequestSpecBuilder()
                    .setBaseUri(ConfigurationReader.get("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public String getJsonPath(Response response, String key)
    {
        String resp=response.asString();
        JsonPath js=new JsonPath(resp);
        return js.get(key).toString();
    }

}
