package main.UI;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ParametrPage {

    private SelenideElement cityField = $(By.xpath("//label[@id='filter-city']//input"));
    private SelenideElement cityCheckBox = $(By.xpath("//label[@class='sbl-filter-checkbox__title' and text()='Москва']"));
    private SelenideElement markField = $(By.xpath("//label[@id='filter-mark']//input"));
    private SelenideElement markCheckBox = $(By.xpath("//label[@class='sbl-filter-checkbox__title' and text()='Audi']"));
    private SelenideElement modelField = $(By.xpath("//label[@id='filter-model']//input"));
    private SelenideElement modelCheckBox = $(By.xpath("//label[@class='sbl-filter-checkbox__title' and text()='e-tron']"));

    private SelenideElement wheelCheckBox = $(By.xpath("//label[@class='horizontal-filter-block__checkbox-title custom-control-label' and text()='полный']"));
    private SelenideElement kppCheckBox = $(By.xpath("//label[@class='horizontal-filter-block__checkbox-title custom-control-label' and text()='автомат']"));
    private SelenideElement fuelType = $(By.xpath("//label[@class='horizontal-filter-block__checkbox-title custom-control-label' and text()='электричество']"));
    private SelenideElement colorField = $(By.xpath("//input[@placeholder='Выберите или введите']"));
    private SelenideElement colorCheckBox = $(By.xpath("//label[@class='sbl-filter-checkbox__title' and text()='синий']"));

    private SelenideElement parametrBtn = $(By.xpath("//a[text()=' Показать все предложения ']"));

    @Step("Подобрать любой автомобиль по параметрам, заполнив все параметры")
    public ParametrPage chooseParametors(String expectedCarMark) {
        cityField.click();
        cityField.setValue("Москва");
        cityCheckBox.click();

        markField.click();
        markField.setValue(expectedCarMark);
        markCheckBox.click();

        modelField.click();
        modelField.setValue("e-tron");
        modelCheckBox.click();

        wheelCheckBox.click();
        kppCheckBox.click();
        fuelType.click();
        colorField.click();
        colorCheckBox.click();

        return this;
    }


    @Step("Нажать на кнопку «Показать все предложения")
    public CarSetPage openShowAllOffersPage() {
        parametrBtn.click();

        return page(CarSetPage.class);
    }
}
