package main.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CarProductPage {

    private SelenideElement h1 = $(By.xpath("//h1"));



    @Step("Проверка соответствия выбранной марки авто, с маркой авто из общего списка на предыдущем шаге")
    public CarProductPage assertCarModel(String expectedCarMark) {
        h1.shouldHave(Condition.partialText(expectedCarMark));

        return this;
    }
}
