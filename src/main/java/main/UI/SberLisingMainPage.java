package main.UI;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SberLisingMainPage {

    private SelenideElement popUp = $(By.xpath("//div[@class='comagic-call-generator__close-btn']"));
    private SelenideElement footerPopUp = $(By.xpath("//button[text()=' Закрыть ']"));
    private SelenideElement parametrBtn = $(By.xpath("//h2[text()='Предложения от дилеров']//following-sibling::div/a"));



    @Step("Переход на страницу параметров СберЛизинг")
    public ParametrPage openParametrPage() {
        try {
            footerPopUp.click();
            popUp.click();
        } catch (UIAssertionError e) {
            e.toString();
        }
        parametrBtn.click();

        return page(ParametrPage.class);
    }
}
