package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\java\\features\\SauceLabs.feature",
glue= {"stepDefinition"})


public class TestRunner {

}