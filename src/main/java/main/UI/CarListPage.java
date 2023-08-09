package main.UI;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class CarListPage {

    //private SelenideElement carModel = $(By.xpath("//a[text()=' Полное описание ']"));
    private ElementsCollection carList = $$(By.xpath("//img[@class='car-card__item-description__image img-fluid rounded-16' and contains(@src, 'iblock')]"));


    @Step("Выбрать модель автомобиля")
    public CarProductPage chooseCarModel() {
        carList.get(0).click();

        return page(CarProductPage.class);
    }
}
