import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        tags = "@test"//,
        //glue = {"gradletutorial.src.test.java.BookStepDefinitions", "src.test.java"
        )
public class BookTest {
}
