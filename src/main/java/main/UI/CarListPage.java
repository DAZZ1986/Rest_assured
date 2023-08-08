package main.UI;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CarListPage {

    private SelenideElement carModel = $(By.xpath("//a[text()=' Полное описание ']"));


    @Step("Выбрать модель автомобиля")
    public CarProductPage chooseCarModel() {
        carModel.click();

        return page(CarProductPage.class);
    }
}
