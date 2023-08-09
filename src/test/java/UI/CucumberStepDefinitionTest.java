package UI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.UI.*;

import static com.codeborne.selenide.Selenide.open;

public class CucumberStepDefinitionTest {
    @Given("Пользователь открыл Google")
    public void пользовательОткрылGoogle() {
        Configuration.timeout = 5000;
        open("https://www.google.com/");
    }

    @When("Ввод в строку поиска google СберЛизинг")
    public void вводВСтрокуПоискаGoogleСберЛизинг() {
        new GooglePage().enterSuarchFieldGooglePage();
    }

    @And("^Переход на сайт СберЛизинг$")
    public void переходНаСайтСберЛизинг() {
        new GooglePage().clickToSberLisingWebSite();
    }

    @And("Переход на страницу параметров СберЛизинг$")
    public void переходНаСтраницуПараметровСберЛизинг$() {
        new SberLisingMainPage().openParametrPage();
    }

    @And("Подобрать любой автомобиль по параметрам, заполнив все параметры {string}")
    public void подобратьЛюбойАвтомобильПоПараметрамЗаполнивВсеПараметрыMark(String expectedCarMark) {
    }
    @And("Подобрать любой автомобиль по параметрам, заполнив все параметры {string} {string} {string}")
    public void подобратьЛюбойАвтомобильПоПараметрамЗаполнивВсеПараметрыCityMarkModel(String City, String Mark, String Model) {
        new ParametrPage().chooseParametors(City, Mark, Model);
    }

    @And("^Нажать на кнопку «Показать все предложения»$")
    public void нажатьНаКнопкуПоказатьВсеПредложения() {
        new ParametrPage().openShowAllOffersPage();
    }

    @And("Выбрать модификацию автомобиля")
    public void выбратьМодификациюАвтомобиля() {
        new CarSetPage().chooseCarSet();
    }

    @And("Выбрать модель автомобиля")
    public void выбратьМодельАвтомобиля() {
        new CarListPage().chooseCarModel();
    }

    @Then("Проверка соответствия выбранной марки авто, с маркой авто из общего списка на предыдущем шаге {string}")
    public void проверкаСоответствияВыбраннойМаркиАвтоСМаркойАвтоИзОбщегоСпискаНаПредыдущемШагеMark(String expectedCarMark) {
        new CarProductPage().assertCarModel(expectedCarMark);
    }


    @After(value = "@close")
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }


}
