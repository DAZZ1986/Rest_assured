package main.UI;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ParametrPage {
    String city = null;
    String mark = null;
    String model = null;

    private SelenideElement cityField = $(By.xpath("//label[@id='filter-city']//input"));
    private SelenideElement cityCheckBox;
    private SelenideElement markField = $(By.xpath("//label[@id='filter-mark']//input"));
    private SelenideElement markCheckBox;
    private SelenideElement modelField = $(By.xpath("//label[@id='filter-model']//input"));
    private SelenideElement modelCheckBox;

    private SelenideElement wheelCheckBox = $(By.xpath("//label[@class='horizontal-filter-block__checkbox-title custom-control-label' and text()='полный']"));
    private SelenideElement kppCheckBox = $(By.xpath("//label[@class='horizontal-filter-block__checkbox-title custom-control-label' and text()='автомат']"));
    private SelenideElement fuelType = $(By.xpath("//label[@class='horizontal-filter-block__checkbox-title custom-control-label' and text()='электричество']"));
    private SelenideElement colorField = $(By.xpath("//input[@placeholder='Выберите или введите']"));
    private SelenideElement colorCheckBox = $(By.xpath("//label[@class='sbl-filter-checkbox__title' and text()='синий']"));

    private SelenideElement parametrBtn = $(By.xpath("//a[text()=' Показать все предложения ']"));




    public void rebuildSelenideElements() {
        String cityCheckBoxLoc = String.format("//label[@class='sbl-filter-checkbox__title' and text()='%s']", city);
        cityCheckBox = $(By.xpath(cityCheckBoxLoc));
        String markCheckBoxLoc = String.format("//label[@class='sbl-filter-checkbox__title' and text()='%s']", mark);
        markCheckBox = $(By.xpath(markCheckBoxLoc));
        String modelCheckBoxLoc = String.format("//label[@class='sbl-filter-checkbox__title' and text()='%s']", model);
        modelCheckBox = $(By.xpath(modelCheckBoxLoc));
    }

    @Step("Подобрать любой автомобиль по параметрам, заполнив все параметры")
    public ParametrPage chooseParametors(String City, String Mark, String Model) {
        city=City;
        mark=Mark;
        model=Model;

        rebuildSelenideElements();

        cityField.click();
        cityField.setValue(City);
        cityCheckBox.click();

        markField.click();
        markField.setValue(Mark);
        markCheckBox.click();

        modelField.click();
        modelField.setValue(Model);
        modelCheckBox.click();

        try {
            wheelCheckBox.click();
            kppCheckBox.click();
            fuelType.click();
            colorField.click();
            colorCheckBox.click();
        } catch (UIAssertionError e) {
            e.toString();
        }

        return this;
    }


    @Step("Нажать на кнопку «Показать все предложения")
    public CarSetPage openShowAllOffersPage() {
        parametrBtn.click();

        return page(CarSetPage.class);
    }
}
