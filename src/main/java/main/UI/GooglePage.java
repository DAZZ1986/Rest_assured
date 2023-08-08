package main.UI;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class GooglePage {
    private SelenideElement suarchField = $(By.xpath("//textarea[@type='search']"));
    private SelenideElement sberLisingLink = $(By.xpath("//h3[starts-with(text(), 'СберЛизинг — официальный сайт лизинговой компании ')]"));
    private SelenideElement translateSuggest = $(By.xpath("//div[text()='Looking for results in English?']//following-sibling::a"));


    @Step("Ввод в строку поиска google.ru СберЛизинг")
    public GooglePage enterSuarchFieldGooglePage() {
        suarchField.setValue("СберЛизинг").pressEnter();

        return this;
    }

    @Step("Переход на сайт СберЛизинг")
    public SberLisingMainPage clickToSberLisingWebSite() {
        try {
            translateSuggest.click();
        } catch (UIAssertionError e) {
            e.toString();
        }
        sberLisingLink.click();

        return page(SberLisingMainPage.class);
    }

}
