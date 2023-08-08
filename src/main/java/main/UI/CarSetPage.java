package main.UI;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class CarSetPage {

    private ElementsCollection carSetList = $$(By.xpath("//a[text()=' Смотреть предложения: 1 ']"));

    @Step("Выбрать модификацию автомобиля")
    public CarListPage chooseCarSet() {
        carSetList.get(0).click();

        return page(CarListPage.class);
    }



}
