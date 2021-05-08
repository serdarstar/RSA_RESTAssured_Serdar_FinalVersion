package com.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/jsonReports/cucumber.json"},
        features = "src/test/java/com/features",
        glue ="C:\\Users\\Serdar Yildiz\\eclipse-workspace\\RSA_API_Cucumber_Framework\\src\\test\\java\\com\\step_definitions" ,
        tags = "@AddPlace"

)
public class CukesRunner {
}
