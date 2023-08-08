package UI;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, //путь до feature файлов для их запуска
        plugin = {"pretty", "html:target/cucumber-reports/report.html"}, //такой отчет
        publish = true)
public class RunCucumberTests {

}
